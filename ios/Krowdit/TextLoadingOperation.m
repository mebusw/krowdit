//
//  TextLoadingOperation.m
//  Krowdit
//
//  Created by jacky on 6/9/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "TextLoadingOperation.h"


@implementation TextLoadingOperation


@synthesize textURL;

/**
 initialize a new UTF-8 text loading operation.
 @param theTextURL of the text
 @param theTarget delegate object
 @param theAction delegate method
 @return self
 */
-(id) initWithTextURL:(NSURL *)theTextURL target:(id)theTarget action:(SEL)theAction tag:(int)theTag {//被
    
    self = [super init];
    if (self) {
        textURL = [theTextURL retain];
        target = theTarget;
        action = theAction;
        tag = theTag;
    }
    return self;
}

-(void) main
{
    DLog(@"textURL=%@", textURL);
    
    NSError *error = nil;
    NSMutableString *text = nil;
    NSData *data = [[NSData alloc] initWithContentsOfURL:textURL options:NSDataReadingMapped error:&error];
    
    if (error){
        NSError *locatedError = [NSError errorWithDomain:NSCocoaErrorDomain code:[error code] userInfo:[NSDictionary dictionary]];
        NSString *errorMessage = [locatedError localizedDescription];
        DLog(@"error message=%@", errorMessage);
        text = [NSMutableString stringWithFormat:@"{\"error\":%d}", TIMEOUT];
    }
    else {
        text = [[[NSMutableString alloc] initWithData:data encoding:NSUTF8StringEncoding] autorelease];
    }
    
    NSString *tagStr = [NSString stringWithFormat:@", \"tag\":%d}", tag]; 
    [text replaceCharactersInRange:NSMakeRange([text length]-1, 1)  withString:tagStr];
    [target performSelectorOnMainThread:action withObject:text waitUntilDone:NO];
    
    [data release];
    [textURL release];

}

@end
