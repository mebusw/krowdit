//
//  KrowditTests.m
//  KrowditTests
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "KrowditTests.h"


@implementation KrowditTests

- (void)setUp
{
    [super setUp];
    
    appDelegate = [[UIApplication sharedApplication] delegate];
    viewController = appDelegate.window.rootViewController;
    view = viewController.view;

}

- (void)tearDown
{
    // Tear-down code here.
    
    [super tearDown];
}

#pragma mark -
#pragma mark helper


#pragma mark -
#pragma mark test cases
- (void)testExample
{
    //STFail(@"Unit tests are not implemented yet in windowbasedTests");
    
}


- (void)testECMacro
{
    STAssertEquals(SUCCESS, 0, @"ec: SUCCESS");
    STAssertEquals(UNSUCCESSFUL, -1, @"ec: UNSUCCESSFUL");    
}

- (void) testAppDelegate {
    STAssertNotNil(appDelegate, @"can't find the app delegate");
}

//- (void) testUIInterfaceOrientation {
//    STAssertTrue([viewController shouldAutorotateToInterfaceOrientation:UIInterfaceOrientationPortrait], @"not support UIInterfaceOrientationPortrait");
//}

- (void) testJSON1 {
    NSString *jsonStr = @"{\"name\":\"jacky\", \"age\":20}";
    //NSURL *url = [NSURL URLWithString:@"http://localhost:8080/k/login.jsp"];
    //jsonStr = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    //NSLog(@"json=%@", jsonStr);
    
    NSDictionary *result = [jsonStr JSONValue];

    STAssertEquals(NSOrderedSame, [[NSString stringWithString:@"jacky"] compare:[result objectForKey:@"name"]], @"name <> jacky");
    STAssertEquals(20, [[result objectForKey:@"age"] integerValue], @"age <> 20");

}

- (void) testJSON3 {
    NSString *jsonStr = @"{\"name\":\"jacky\", \"age\":20, \"remember\":true, \"fail\":false}";
    //NSURL *url = [NSURL URLWithString:@"http://localhost:8080/k/login.jsp"];
    //jsonStr = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    //NSLog(@"json=%@", jsonStr);
    
    NSDictionary *result = [jsonStr JSONValue];
    
    STAssertEquals(NSOrderedSame, [[NSString stringWithString:@"jacky"] compare:[result objectForKey:@"name"]], @"name <> jacky");
    STAssertEquals(20, [[result objectForKey:@"age"] integerValue], @"age <> 20");
    DLog(@"remember %@ %@", [result objectForKey:@"remember"], [result objectForKey:@"fail"]);
    STAssertTrue(YES == [[result objectForKey:@"remember"] integerValue], @"remember <> true");
    STAssertTrue(NO == [[result objectForKey:@"fail"] integerValue], @"fail <> false");
    
    
}
@end
