//
//  HTTPPostOffice.h
//  Krowdit
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "PostOfficeProtocal.h"
#import "ImageLoadingOperation.h"
#import "TextLoadingOperation.h"
#import "Reachability.h"
/**
 HTTPPostOffice is an implementation of PostOfficeProtocal with HTTP protocal
 
 @see PostOfficeProxy, PostOfficeProtocal
 */
@interface HTTPPostOffice : NSObject <PostOfficeProtocal> {
@private
    NSOperationQueue *opQueue;  //the multi-thread operation queue
    NSURLConnection *cometConn; //the connction for comet connection to push
    id <DataPushDelegate> dataPushDelegate;
}

@end

