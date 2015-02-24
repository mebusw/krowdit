//
//  SignupViewController.h
//  Krowdit
//
//  Created by jacky on 5/31/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PostOfficeProtocal.h"

@interface SignupViewController : UIViewController <DataPullDelegate> {
    
    UITextField *uesrName;
    UITextField *emailAddress;
    UITextField *passWord;
    UITextField *verifyPassword;
    UIAlertView *loadingView;
    UIAlertView *alert;
    
}

@property (nonatomic, retain) UISegmentedControl *sc;
@property (nonatomic, retain )IBOutlet UITextField *userName;
@property (nonatomic, retain )IBOutlet UITextField *emailAddress;
@property (nonatomic, retain )IBOutlet UITextField *passWord;
@property (nonatomic, retain )IBOutlet UITextField *verifyPassword;
@property (nonatomic, retain) UIAlertView *loadingView;
@property (nonatomic, retain) UIAlertView *alert;


- (IBAction) switchBackToSignin;
- (IBAction) textFieldDoneEditing:(id)sender;
- (BOOL) validateEmail:(NSString *) candidate;
- (IBAction) signup;
//- (void) signingUp;
- (void) waiting;
- (void) alertShow;


@end
