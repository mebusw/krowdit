//
//  TextLoadingOperation.h
//  Krowdit
//
//  Created by jacky on 6/9/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface TextLoadingOperation : NSOperation {
    @private
    id target;
    SEL action;
    int tag;
}

/**
 the URL
 */
@property (nonatomic,retain) NSURL *textURL;

/**
 initiate a request which will callback with some text
 @param theTextURL URL of the HTTP request 
 @param theTarget the delegate object.
 @param theAction the delegate method, defined like @selector(didGetText:(NSString*)obj)
 @return self

 */
-(id)initWithTextURL:(NSURL *)theTextURL target:(id)theTarget action:(SEL)theAction tag:(int)theTag;

@end
