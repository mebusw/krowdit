//
//  JoinKrowdViewControllerTest.m
//  Krowdit
//
//  Created by Jia Yong on 7/5/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "JoinKrowdViewControllerTest.h"

@implementation JoinKrowdViewControllerTest

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

-(void)testgetImageByName{
    
    testJoinKrowdViewController = [[JoinKrowdViewController alloc] init];
    
    UIImage * imgForDict1 = [UIImage imageNamed:@"img1.png"];
    UIImage * imgForDict2 = [UIImage imageNamed:@"img2.png"];
    
    //    NSLog(@"testJoinKrowdViewController is %@",testJoinKrowdViewController);
    //    NSLog(@"before init dict %@",testJoinKrowdViewController.imageDownloadsInProgress);
    testJoinKrowdViewController.imageDownloadsInProgress = [[NSMutableDictionary alloc]initWithObjectsAndKeys:imgForDict1,@"pic1",imgForDict2,@"pic2",nil];
    //    NSLog(@"after init , dict is %@",testJoinKrowdViewController.imageDownloadsInProgress);
    //    NSLog(@"%@",[testJoinKrowdViewController getImageByName:@"pic1"]);
    //    NSLog(@"%@",[testJoinKrowdViewController getImageByName:@"pic5"]);
    STAssertNotNil([testJoinKrowdViewController getImageByName:@"pic1"],@"can't find the pic1");
    STAssertNil([testJoinKrowdViewController getImageByName:@"pic5"],@"should be null");
    
}
@end
