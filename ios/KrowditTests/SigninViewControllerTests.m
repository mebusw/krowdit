//
//  SigninViewControllerTests.m
//  Krowdit
//
//  Created by jyong on 6/23/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "SigninViewControllerTests.h"


@implementation SigninViewControllerTests
- (void)setUp
{
    [super setUp];
    
    appDelegate = [[UIApplication sharedApplication] delegate];
    
}

- (void)tearDown
{
    // Tear-down code here.
    
    [super tearDown];
}

-(void)testDidLogin{
    testSigninViewController = (SigninViewController *)[appDelegate.userNav topViewController];
    NSString * str1 = [NSString stringWithFormat:@"{\"uid\":6,\"error\":%d, \"tag\":1}", SUCCESS];
    [testSigninViewController didLogin:str1];
    NSLog(@"%@",testSigninViewController);
    NSLog(@"%@",appDelegate);
    NSLog(@"%@",appDelegate.userNav);
    NSLog(@"%@",appDelegate.tabBar);
    STAssertNotNil(testSigninViewController, @"can't find the app delegate");
    STAssertNotNil(appDelegate, @"can't find the app delegate");
    STAssertNotNil(appDelegate.userNav, @"can't find the app delegate");
    STAssertNotNil(appDelegate.tabBar, @"can't find the app delegate");
    viewController = appDelegate.window.rootViewController;
    NSLog(@"c:%@%@", viewController, appDelegate.tabBar);
    STAssertTrue(appDelegate.tabBar == viewController,@"fail go to maintab");



}

@end
