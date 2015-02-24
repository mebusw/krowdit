//
//  KrowditAppDelegate.m
//  Krowdit
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "KrowditAppDelegate.h"
#import "SigninViewController.h"
#import "PostOfficeProxy.h"
#import "FBConnect/FBConnect.h"
/**
 auto generated by <b>templates</b>.
 */
@implementation KrowditAppDelegate


@synthesize window=_window;

@synthesize tabBar;
@synthesize operationQueue;
@synthesize sc;
@synthesize userNav;
@synthesize kroNav;
@synthesize userBean;
@synthesize krowdBean;

#pragma mark -
#pragma mark view controller switching

/**
 switch user navigation screen to next one: main tab bar view controller, which contains all the core functions etc.
 1. 
 2. 
 */
- (void) switchUserNavToMainTabBar {
    //1.
    [userNav popToRootViewControllerAnimated:NO];
    [userNav popViewControllerAnimated:NO];

    //2
    self.window.rootViewController = tabBar;
    tabBar.selectedIndex = 0;


}

- (IBAction) backtousernav {
    self.window.rootViewController = userNav;
    DLog(@"kid=%d", self.krowdBean.krowdId);
    

}

#pragma mark -
#pragma mark application life cycle


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    //1. init global beans
    userBean = [[UserBean alloc] init];
    self.userBean.imageName = @"no_photo.gif";
    
    krowdBean = [[KrowdBean alloc] init];

    //2. get a path of the memoried login infomation
    //TODO auto login will take half a second

    
    NSDictionary * myDictionary;
    NSArray *documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	NSString *documentDirectory = [documentPaths objectAtIndex:0];
	NSString * dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.plist"];

    // 3. if the memoried login infomation exits and then call proxy to login
    if ([[NSFileManager defaultManager]fileExistsAtPath:dataFilePath]) {      
        myDictionary = [[NSDictionary alloc]initWithContentsOfFile:dataFilePath];
        NSString * strUserName = [myDictionary objectForKey:@"UserName"];
        NSString * strPassword = [myDictionary objectForKey:@"PassWord"];
        int strUserType = [[myDictionary objectForKey:@"UserType"] intValue];
       
        //DLog(@"UserName from login memory is %@",strUserName);
        self.userBean.userName = strUserName;
        self.userBean.userType = strUserType;

        PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
        [po loginWithUserName:strUserName password:strPassword userType:strUserType target:self tag:0];
        [myDictionary release];

	}
    else {
        self.window.rootViewController = userNav;
    }
    
    
    //hide the "Edit" button in the MoreViewController
    self.tabBar.customizableViewControllers = nil;
    
    return YES;
}

/**
pass the url to facebook object
 */
- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
    return [[(SigninViewController*)[userNav topViewController] facebook] handleOpenURL:url];
    //if use the following line, the screen with success fb will link to login view, that is wrong
    //return [[[[SigninViewController alloc]init] facebook] handleOpenURL:url];
}


/**
 switch to main view for successful logging in
 */

- (void) didLogin:(NSString *)string
{

    //DLog(@"string is %@", string);
    NSDictionary *result=[string JSONValue];
    int error=[[result objectForKey:@"error"] intValue];
    switch (error) {
        case SUCCESS:
            self.userBean.userId = [[result objectForKey:@"uid"] intValue];
            self.userBean.imageName = [result objectForKey:@"imageName"];
            [self switchUserNavToMainTabBar];
            break;
        default:
            self.userBean.userId = 0;
            self.userBean.userName = nil;
            self.window.rootViewController = userNav;
            
            break;
    }
}
- (void)applicationWillResignActive:(UIApplication *)application
{
    /*
     Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
     Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
     */
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    /*
     Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
     If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
     */
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    /*
     Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
     */
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    /*
     Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
     */
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    /*
     Called when the application is about to terminate.
     Save data if appropriate.
     See also applicationDidEnterBackground:.
     */
    [PostOfficeProxy attemptDealloc];
}

- (void)dealloc
{
    [krowdBean release];
    [kroNav release];
    [userBean release];
    [_window release];
    [super dealloc];
}

@end
