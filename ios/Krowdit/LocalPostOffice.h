//
//  LocalPostOffice.h
//  Krowdit
//
//  Created by jacky on 7/14/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "PostOfficeProtocal.h"

/**
 This PostOffice is not really retrive data from server, but generate some fake data locally.
 */
@interface LocalPostOffice : NSObject <PostOfficeProtocal> {
    
}

@end
