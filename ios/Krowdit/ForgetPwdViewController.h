//
//  ForgetPwdViewController.h
//  Krowdit
//
//  Created by jacky on 7/11/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface ForgetPwdViewController : UIViewController  {
    
}

-(IBAction) clickSend;
-(IBAction) clickCancel;
@property (nonatomic, retain) IBOutlet UITextField *emailAddr;

@end
