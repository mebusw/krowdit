//
//  ForgetPwdViewController.m
//  Krowdit
//
//  Created by jacky on 7/11/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "PostOfficeProxy.h"
#import "ForgetPwdViewController.h"
#import "SBJson.h"
#import "SigninViewController.h"
#import "SignupViewController.h"


#define BTN_NO      0
#define BTN_SURE    1

@implementation ForgetPwdViewController

@synthesize emailAddr;

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

- (BOOL) validateEmail:(NSString *) candidate {
    NSString *emailRegex = @"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}"; 
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex]; 
    
    return [emailTest evaluateWithObject:candidate];
    
}

-(IBAction) clickCancel {
    [self dismissModalViewControllerAnimated:YES];
}

-(IBAction) clickSend {
    BOOL isEmailValidate=[self validateEmail:emailAddr.text];
    if (!isEmailValidate) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"The Email is not correct" 
                                                        message:nil
                                                       delegate:self
                                              cancelButtonTitle:@"Roger that"
                                              otherButtonTitles:nil];
        [alert show];
        [alert release];
    } else {
        PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
        [po recoverPasswordWithEmail:emailAddr.text target:self tag:0];
    }
    
}

- (void) didRecoverPassword: (NSString *)string {
    UIAlertView *alert;
    NSDictionary *json = [string JSONValue];
    int ec = [[json objectForKey:@"error"] intValue];
    DLog(@"ec=%d", ec);
    switch (ec) {
        case SUCCESS:
            alert = [[UIAlertView alloc] initWithTitle:@"Your Password has been sent" 
                                               message:nil
                                              delegate:self
                                     cancelButtonTitle:@"Roger that"
                                     otherButtonTitles:nil];
            [alert show];
            [alert release];
            [self dismissModalViewControllerAnimated:YES];
            break;
        case NOT_FOUND:
            alert = [[UIAlertView alloc] initWithTitle:@"The account doesn't exist, want to signup?" 
                                               message:nil
                                              delegate:self
                                     cancelButtonTitle:@"No, thanks"
                                     otherButtonTitles:@"Sure", nil];
            [alert show];
            [alert release];
            break;
        case TIMEOUT:
            alert = [[UIAlertView alloc] initWithTitle:@"Connection Lost" 
                                               message:nil
                                              delegate:self
                                     cancelButtonTitle:@"Roger that"
                                     otherButtonTitles:nil];
            [alert show];
            [alert release];
            break;            
        default:
            break;
    }
    
    
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    UINavigationController* userNav;
    
    switch (buttonIndex) {
        case BTN_NO:
            
            break;
        case BTN_SURE:
            userNav = (UINavigationController *)[self parentViewController];
            [(SigninViewController*) (userNav.topViewController) switchFromSigninToSignup];
            break;
        default:
            break;
    }
    [self dismissModalViewControllerAnimated:YES];
}

@end
