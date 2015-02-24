//
//  SigninViewController.h
//  Krowdit
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PostOfficeProtocal.h"
#import "FBConnect/FBConnect.h"


@interface SigninViewController : UIViewController <DataPullDelegate,FBRequestDelegate,
FBDialogDelegate,
FBSessionDelegate> {
    
    UITextField *userName;
    UITextField *passWord;
    UIAlertView *loadingView;
    UIAlertView *alert;
    Facebook* _facebook;
    NSArray* _permissions;
    
}

@property (nonatomic, retain) UISegmentedControl *sc;
@property (nonatomic, retain) IBOutlet UITextField *userName;
@property (nonatomic, retain) IBOutlet UITextField *passWord;
@property (nonatomic, retain) UIAlertView *loadingView;
@property (nonatomic, retain) UIAlertView *alert;
@property(readonly) Facebook *facebook;

-(IBAction)fbButtonClick:(id)sender;
- (IBAction) login;
- (IBAction) switchFromSigninToSignup;
- (IBAction)textFieldDoneEditing:(id)sender;
- (void) loggingIn;
- (void) loginMemoryWithUserName:(NSString *)loginUserName password: (NSString *)loginPassword userType:(NSString *)loginUserType;

- (void) alertShow;
- (void) waiting;

- (IBAction) ForgetPassword;
- (void) alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex;

@end
