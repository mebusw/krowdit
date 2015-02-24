//
//  KrowditTests.h
//  KrowditTests
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <SenTestingKit/SenTestingKit.h>
#import "KrowditAppDelegate.h"
#import "SBJson.h"

@interface KrowditTests : SenTestCase {
@private 
    KrowditAppDelegate *appDelegate;
    UIViewController *viewController;
    UIView *view;
}
@end
