//
//  ImageLoadingOperation.h
//  PostOffice
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface ImageLoadingOperation : NSOperation {
    @private
    id target;
    SEL action;
    int tag;
}

/**
 the URL
 */
@property (nonatomic,retain) NSURL *imageURL;

/**
 initiate a request which will callback with an image.
 @param theTextURL URL of the HTTP request 
 @param theTarget the delegate object.
 @param theAction the delegate method, defined like @selector(didGetImage:(id)obj)
 @return self
 
 */
-(id)initWithImageURL:(NSURL *)theImageURL target:(id)theTarget action:(SEL)theAction tag:(int)theTag;


@end
