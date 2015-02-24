//
//  SigninViewController.m
//  Krowdit
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "SigninViewController.h"
#import "SignupViewController.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "EC.h"
#import "FBConnect/FBConnect.h"
#import "ForgetPwdViewController.h"

@implementation SigninViewController

@synthesize sc;
@synthesize userName;
@synthesize passWord;
@synthesize loadingView;
@synthesize alert;
@synthesize facebook = _facebook;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

/**
 implement the callback method to the Facebook object
 */
- (void) loginMemoryWithUserName:(NSString *)loginUserName password: (NSString *)loginPassword userType:(NSString *)loginUserType{
    NSString *strForloginUserName = [NSString stringWithFormat:@"%@",[loginUserName stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    NSString *strForloginPassword = [NSString stringWithFormat:@"%@",[loginPassword stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    DLog(@"strForloginPassword is %@",strForloginPassword);
    NSDictionary * myDictionary = [NSDictionary dictionaryWithObjectsAndKeys:strForloginUserName,@"UserName",strForloginPassword,@"PassWord",loginUserType,@"UserType",nil];
    NSArray *documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentDirectory = [documentPaths objectAtIndex:0];
    NSString * dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.plist"];
    [myDictionary writeToFile:dataFilePath atomically:YES];
}
- (void)fblogin {
    NSLog(@"%@",self);
    [_facebook authorize:_permissions delegate:self];
}
- (IBAction)fbButtonClick:(id)sender {
    
    [self fblogin];
    
}
- (void)request:(FBRequest *)request didLoad:(id)result {
    DLog(@"result is %@",result);
    DLog(@"last_name is %@",[result objectForKey:@"last_name"]);
    if ([result isKindOfClass:[NSArray class]]) {
        result = [result objectAtIndex:0];
    }
    NSString * strFBname = [result objectForKey:@"name"];
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po loginWithUserName:strFBname password:nil userType:FACEBOOK_USER target:self tag:1];
    [self loginMemoryWithUserName:strFBname password:nil userType:@"2"];
//    [po loginWithUserName:@"jyong 009" password:nil userType:FACEBOOK_USER target:self tag:1];
};
- (void)fbDidLogin{
    
    [_facebook requestWithGraphPath:@"me" andDelegate:self];
    
    
}
- (void)dealloc
{
    [userName release];
    [passWord release];
    [loadingView release];
    [super dealloc];
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
    
    _facebook = [[Facebook alloc] initWithAppId:@"196446450401279"];
    
    sc = [[UISegmentedControl alloc] initWithItems:[NSArray arrayWithObjects:@"Signin", @"Signup", nil]];
    sc.segmentedControlStyle = UISegmentedControlStyleBar;
    sc.selectedSegmentIndex = 0;
    self.navigationItem.titleView = sc;
    [sc addTarget:self action:@selector(switchFromSigninToSignup) forControlEvents:UIControlEventValueChanged];
    [sc release];
    
    
}


- (void)viewWillAppear:(BOOL)animated {
    //DLog(@"vwa 1");
    sc = (UISegmentedControl*) self.navigationItem.titleView;
    sc.selectedSegmentIndex = 0;
    [sc addTarget:self action:@selector(switchFromSigninToSignup) forControlEvents:UIControlEventValueChanged];
    
}

- (void)viewWillDisappear:(BOOL)animated {
    [sc removeTarget:self action:@selector(switchFromSigninToSignup) forControlEvents:UIControlEventValueChanged];
    
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

- (IBAction) login {
    
    if ([userName.text isEqualToString:@""]) {
        alert=[[UIAlertView alloc] 
               initWithTitle:@"Username can not be empty"
               message:nil delegate:self 
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
    
    else
    {
        //DLog(@"Test");
        [self waiting];
        KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
        appDelegate.userBean.userName = userName.text;
        PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
        [po loginWithUserName:userName.text password:passWord.text userType:KROWDIT_USER target:self tag:0];
    }
}

- (IBAction) switchFromSigninToSignup {
    //DLog(@"switch");
    SignupViewController *signupScreen = [[SignupViewController alloc] init];
    signupScreen.navigationItem.backBarButtonItem = nil;
    signupScreen.navigationItem.hidesBackButton = YES;
    [self.navigationController pushViewController:signupScreen animated:NO];
    [signupScreen release];
    
}
- (IBAction)textFieldDoneEditing:(id)sender
{
    [sender resignFirstResponder];
}
- (void) didLogin:(NSString *)string
{
    NSDictionary *result=[string JSONValue];
    NSLog(@"%@",[result description]);
    if (0==[[result objectForKey:@"tag"] intValue]) {
        
        int error=[[result objectForKey:@"error"] intValue];
        [loadingView dismissWithClickedButtonIndex:0 animated:YES];
        
        switch (error) {
            case NOT_FOUND:
                alert=[[UIAlertView alloc] 
                       initWithTitle:@"Username or PassWord error!" 
                       message:nil
                       delegate:self 
                       cancelButtonTitle:@"Close" 
                       otherButtonTitles:nil, nil];
                
                [self alertShow];
                break;
            case SUCCESS:
                [self loggingIn];
                [self loginMemoryWithUserName:userName.text password:passWord.text userType:@"1"];
                break;
                
            default:
                break;
        }
        
    }else if (1==[[result objectForKey:@"tag"] intValue]){
        KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
        [appDelegate switchUserNavToMainTabBar];
    }
}
- (void) loggingIn {
    
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    [appDelegate switchUserNavToMainTabBar];
}
- (void) waiting
{
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

- (IBAction) ForgetPassword
{
    ForgetPwdViewController *forgetPwdViewController = [[[ForgetPwdViewController alloc] init] autorelease];
    forgetPwdViewController.modalPresentationStyle = UIModalPresentationFormSheet;

    [self presentModalViewController:forgetPwdViewController animated:YES]; 
    
}



/** This function is an action which can send email when you type the email address*/

- (void) alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    switch (buttonIndex) {
        case 0:
            break;
        case 1:
            //DLog(@"send to you %@", myField.text  );
        default:
            break;
    }
}



- (void) alertShow
{
    [alert show];
    [alert release];
}

@end
