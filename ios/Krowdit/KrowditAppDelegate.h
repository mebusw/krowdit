//
//  KrowditAppDelegate.h
//  Krowdit
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SBJson.h"
#import "FBDialog.h"
#import "FBLoginDialog.h"
#import "FBLoginButton.h"
#import "FBConnect.h"
#import "UserBean.h"
#import "KrowdBean.h"

@interface KrowditAppDelegate : NSObject <UIApplicationDelegate> {

}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UITabBarController *tabBar;
@property (nonatomic, retain) IBOutlet UINavigationController *userNav;
@property (nonatomic, retain) IBOutlet UINavigationController *kroNav;
@property (nonatomic, retain) UISegmentedControl *sc;
@property (nonatomic, retain) NSOperationQueue *operationQueue;
@property (nonatomic, retain) UserBean *userBean;
@property (nonatomic, retain) KrowdBean *krowdBean;

- (void) didLogin:(NSString *)string;

- (void) switchUserNavToMainTabBar;

- (IBAction) backtousernav;

@end
