//
//  PostOfficeProxy.h
//  Krowdit
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "PostOfficeProtocal.h"

/** 
 PostOfficeProxy is a proxy to real implementation of communication protocal, either http or socket.
 
 @see HTTPPostOffice
 */
@interface PostOfficeProxy :NSObject <PostOfficeProtocal> {
    @private
    id<PostOfficeProtocal> realPostOffice; // the real PostOffice under the proxy, can be HTTP or Socket 
    
}

+ (PostOfficeProxy*) sharedPostOfficeProxy;
+ (void) attemptDealloc;




@end
