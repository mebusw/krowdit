//
//  KrowditAppDelegateTest.m
//  Krowdit
//
//  Created by jyong on 6/23/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "KrowditAppDelegateTest.h"


@implementation KrowditAppDelegateTest

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
-(void)testdidLoginSUCCESS{
    
//    NSString * str1 = @"{\"uid\":1,\"error\":SUCCESS}";
    NSString * str1 = [NSString stringWithFormat:@"{\"error\":%d}", SUCCESS];

    [appDelegate didLogin:str1];
    viewController = appDelegate.window.rootViewController;

    STAssertEquals(appDelegate.tabBar, viewController,@"fail go to MainTab");
}

-(void)testdidLoginUNSUCCESS{
    
    //NSString * str1 = @"{\"uid\":1,\"error\":1}";
    NSString * str1 = [NSString stringWithFormat:@"{\"error\":%d}", TIMEOUT];
    //NSString * str1 = @"{\"error\":TIMEOUT}";
    [appDelegate didLogin:str1];
    
    viewController = appDelegate.window.rootViewController;
    STAssertNotNil(viewController, @"can't find the app delegate");
    STAssertNotNil(appDelegate, @"can't find the app delegate");
//    NSLog(@"%@",viewController);
//    NSLog(@"%@",appDelegate);
    
    
    STAssertEquals(appDelegate.userNav, viewController,@"fail go to userNav");
    
}
@end
