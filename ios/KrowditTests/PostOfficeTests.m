//
//  PostOfficeTests.m
//  Krowdit
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "PostOfficeTests.h"


@implementation PostOfficeTests



- (void)setUp
{
    [super setUp];
    po = [PostOfficeProxy sharedPostOfficeProxy];
    isCalledBack = YES;
    
}

- (void)tearDown
{
    // Tear-down code here.
    STAssertTrue(YES == isCalledBack, @"call back method is not invoked");
    [super tearDown];
}

#pragma mark -
#pragma mark helper

-(void) didGetImage: (id) obj {
    /* the call back seems not called if it has an async operation.
       that means the test suite is faster and finish before the async operation invoking the callback function.

     
    Asynchronous callbacks often require a message loop to run. It is a frequent pattern to stop the message loop after callback was called in the test code. Otherwise the loop is just waiting for next tasks, and there will be none.    
     http://blog.moonshine-project.com/en/2009/11/22/asynchronous-unit-testing-with-ghunit-and-nsinvocation/
     */
    NSLog(@"didGetImage, %@", obj);
    
}

-(void) didLogin:(NSString *)string {
    NSLog(@"didLogin, %@", string);
}


#pragma mark -
#pragma mark test cases

- (void) testSyncLogin {
    [self performSelector:@selector(didLogin:) withObject:@"fake!!!"];
}


- (void) testAsyncLogin {
    [po loginWithUserName:@"ja cky" password:@"123" userType:KROWDIT_USER target:self tag:0];
    
}



- (void) testAsyncGetImage {
    [po getImage:@"gua.jpg" target:self tag:0];

}




    




-(void) testSharedPostOffice {
    
    STAssertNotNil(po, @"post office is not open today");
    
    
}
-(void) testSingleton {
    STAssertNotNil(po, @"post office is not open today");
    [po release];
    STAssertNotNil(po, @"post office is realease by accident");
}

-(void) testSingleton2 {
    STAssertNotNil(po, @"post office is not open today");
    po = [PostOfficeProxy sharedPostOfficeProxy];
    STAssertNotNil(po, @"post office is alloced again");
}

-(void) testSingleton3 {
    STAssertNotNil(po, @"post office is not open today");
    po = [PostOfficeProxy alloc];
    STAssertNotNil(po, @"post office is alloced 3rd time");
}






@end
