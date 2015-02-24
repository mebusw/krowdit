//
//  SignupViewController.m
//  Krowdit
//
//  Created by jacky on 5/31/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "SignupViewController.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "EC.h"

@implementation SignupViewController

@synthesize sc;
@synthesize userName;
@synthesize emailAddress;
@synthesize passWord;
@synthesize verifyPassword;
@synthesize loadingView;
@synthesize alert;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)dealloc
{
    
    [userName release];
    [emailAddress release];
    [passWord release];
    [verifyPassword release];
    [loadingView release];
    [super dealloc];
    [sc removeTarget:self action:@selector(switchBackToSignin) forControlEvents:UIControlEventValueChanged];
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    sc = [[UISegmentedControl alloc] initWithItems:[NSArray arrayWithObjects:@"Signin", @"Signup", nil]];
    sc.segmentedControlStyle = UISegmentedControlStyleBar;
    sc.selectedSegmentIndex = 1;
    self.navigationItem.titleView = sc;
    
    self.navigationItem.backBarButtonItem = nil;
    self.navigationItem.leftBarButtonItem = nil;
    
}

- (void)viewWillAppear:(BOOL)animated {
    [sc addTarget:self action:@selector(switchBackToSignin) forControlEvents:UIControlEventValueChanged];
    
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

#pragma mark -

- (IBAction) switchBackToSignin {
    [self.navigationController popViewControllerAnimated:NO];
    
}
- (IBAction) textFieldDoneEditing:(id)sender {
    [sender resignFirstResponder];
}

- (BOOL) validateEmail:(NSString *) candidate {
    NSString *emailRegex = @"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}"; 
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex]; 
    
    return [emailTest evaluateWithObject:candidate];
    
}
- (IBAction) signup
{
    BOOL isEmailValidate=[self validateEmail:emailAddress.text];
    if ([userName.text isEqualToString:@""]) 
    {
        alert=[[UIAlertView alloc] 
               initWithTitle:@"Username can not be empty!" 
               message:nil
               delegate:self 
               cancelButtonTitle:@"Close" 
               otherButtonTitles:nil, nil];
        
        [self alertShow];
        
    }
    else if([emailAddress.text isEqualToString:@""])
    {
        alert=[[UIAlertView alloc] 
               initWithTitle:@"Email can not be empty!" 
               message:nil
               delegate:self 
               cancelButtonTitle:@"Close" 
               otherButtonTitles:nil, nil];
        
        [self alertShow];
        
    }
    else if(!isEmailValidate)
    {
        alert=[[UIAlertView alloc] 
               initWithTitle:@"Email type is not right!" 
               message:nil
               delegate:self 
               cancelButtonTitle:@"Close" 
               otherButtonTitles:nil, nil];
        
        [self alertShow];
        
    }
    else if([passWord.text isEqualToString:@""])
    {
        alert=[[UIAlertView alloc] 
               initWithTitle:@"Password can not be empty!" 
               message:nil
               delegate:self 
               cancelButtonTitle:@"Close" 
               otherButtonTitles:nil, nil];
        
        [self alertShow];
        
    }
    else if(![passWord.text isEqualToString:verifyPassword.text])
    {
        alert=[[UIAlertView alloc] 
               initWithTitle:@"The password you type two times are different!" 
               message:nil
               delegate:self 
               cancelButtonTitle:@"Close" 
               otherButtonTitles:nil, nil];
        
        [self alertShow];
    }
    else
    {
        [self waiting];
        PostOfficeProxy *po = [PostOfficeProxy sharedPostOfficeProxy];
        [po signupWithUserName:userName.text password:passWord.text email:emailAddress.text target:self tag:0];
        
    }
    
    
}

- (void) didSignup:(NSString *)string {
    /** Receive a message from server which is used to judge 
     whether the process of registration is success or not*/
    NSDictionary *result=[string JSONValue];
    
    int error=[[result objectForKey:@"error"] intValue];
    [loadingView dismissWithClickedButtonIndex:0 animated:YES];
    switch (error) {
        case EXISTED:
            alert=[[UIAlertView alloc] 
                   initWithTitle:@"Username already existed!"
                   message:nil
                   delegate:self 
                   cancelButtonTitle:@"Close" 
                   otherButtonTitles:nil, nil];
            
            [self alertShow];
            break;
            
        case SUCCESS:
//            [NSThread detachNewThreadSelector:@selector(waiting) toTarget:self withObject:nil];
//            [NSThread sleepForTimeInterval:1];
            alert=[[UIAlertView alloc] 
                   initWithTitle:@"Register Success!"
                   message:nil
                   delegate:self 
                   cancelButtonTitle:@"Close" 
                   otherButtonTitles:nil, nil];
            NSDictionary * myDictionary = [NSDictionary dictionaryWithObjectsAndKeys:userName.text,@"UserName",passWord.text,@"password",nil];
            NSArray *documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
            NSString *documentDirectory = [documentPaths objectAtIndex:0];
            NSString * dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.plist"];
            [myDictionary writeToFile:dataFilePath atomically:YES];

            //[self alertShow];
            break;
            
        default:
            alert=[[UIAlertView alloc] 
                   initWithTitle:@"Connection Error!" 
                   message:nil
                   delegate:self 
                   cancelButtonTitle:@"Close" 
                   otherButtonTitles:nil, nil];
            
            [self alertShow];
            break;
    }
    
    
}

-(void) waiting {
    NSAutoreleasePool *pool=[[NSAutoreleasePool alloc] init];
    loadingView = [[UIAlertView alloc] initWithTitle:nil 
                                             message: @"Please Waiting... ..."
                                            delegate: self
                                   cancelButtonTitle: nil
                                   otherButtonTitles: nil];
    
    UIActivityIndicatorView *activityView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhite];
    activityView.frame = CGRectMake(120.f, 48.0f, 37.0f, 37.0f);
    [loadingView addSubview:activityView];
    [activityView startAnimating];
    [activityView release];
    
    [loadingView show];
    
    
    //[loadingView dismissWithClickedButtonIndex:0 animated:YES];
    [pool release];
    
}


- (void) alertShow {
    [alert show];
    [alert release];
}


@end
