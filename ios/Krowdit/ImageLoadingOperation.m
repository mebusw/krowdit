//
//  ImageLoadingOperation.m
//  PostOffice
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "ImageLoadingOperation.h"


@implementation ImageLoadingOperation


@synthesize imageURL;

/**
 initialize a new image loading operation.
 @param theImageURL URL of the image
 @param theTarget delegate object
 @param theAction delegate method
 @return self
 */
-(id) initWithImageURL:(NSURL *)theImageURL target:(id)theTarget action:(SEL)theAction tag:(int)theTag{
    
    self = [super init];
    if (self) {
        imageURL = [theImageURL retain];
        target = theTarget;
        action = theAction;
        tag = theTag;

    }
    return self;
}

-(void) main
{
    DLog(@"imageURL=%@", imageURL);
    
    NSError *error = nil;
    NSData *data = [[NSData alloc] initWithContentsOfURL:imageURL options:NSDataReadingMapped error:&error];
    [imageURL release];
    UIImage *image = nil;
    NSString *jsonStr = nil;
    
    
    if (error){
        NSError *locatedError = [NSError errorWithDomain:NSCocoaErrorDomain code:[error code] userInfo:[NSDictionary dictionary]];
        NSString *errorMessage = [locatedError localizedDescription];
        DLog(@"error message=%@", errorMessage);
        jsonStr = [NSString stringWithFormat:@"{\"error\":%d, \"tag\":%d}", TIMEOUT, tag];
    }
    else {
        image = [UIImage imageWithData:data];
        jsonStr = [NSString stringWithFormat:@"{\"error\":%d, \"tag\":%d}", SUCCESS, tag];
        
    }
    [data release];
    NSDictionary *result = [NSDictionary dictionaryWithObjectsAndKeys:jsonStr, @"json", image, @"image", nil];
    [target performSelectorOnMainThread:action withObject:result waitUntilDone:NO];
    

}
@end
