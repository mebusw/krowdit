//
//  HTTPPostOffice.m
//  Krowdit
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "HTTPPostOffice.h"
#import "SBJson.h"
/**
 communication to server with HTTP 1.1 protocal
 */

#define SERVER_HOST     @"192.168.1.113"
#define SERVLET_URL     @"http://192.168.1.113:8080/servlet/"
#define IMAGE_URL       @"http://192.168.1.113:8080/image/"
#define COMET_URL       @"http://192.168.1.113:8080/comet/"

@implementation HTTPPostOffice

- (id) init {
    
    self = [super init];
    if (nil != self) {
        opQueue = [[NSOperationQueue alloc] init];
    }
    return self;
}

- (void)dealloc {
    [opQueue release];
    //    [cometConn release];
    
    [super dealloc];
    
}

#pragma mark -
#pragma mark delegate PostOfficeProtocal - reachability

-(BOOL)reachable {
    Reachability *r = [Reachability reachabilityWithHostName:SERVER_HOST];
    NetworkStatus internetStatus = [r currentReachabilityStatus];
    if(internetStatus == NotReachable) {
        return NO;
    }
    return YES;
}

#pragma mark -
#pragma mark delegate PostOfficeProtocal - synchorous


-(NSString*) loginWithUserName: (NSString*)userName password: (NSString*)password {
    NSString *strForUrlOfuserName = [NSString stringWithFormat:@"%@",[userName stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    NSString *strForUrlOfPassword = [NSString stringWithFormat:@"%@",[password stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    NSString *urlStr = [NSString stringWithFormat:@"%Login?userName=%@&pwd=%@", SERVLET_URL, strForUrlOfuserName, strForUrlOfPassword];
    NSURL *url = [NSURL URLWithString:urlStr];
    NSString *result = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    
    return result;
} 


#pragma mark -
#pragma mark delegate PostOfficeProtocal - asynchorous

-(void) loginWithUserName: (NSString*)userName password: (NSString*)password userType: (int)userType target: (id)target tag:(int)tag {
    NSString *strForUrlOfuserName = [NSString stringWithFormat:@"%@",[userName stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    NSString *strForUrlOfPassword = [NSString stringWithFormat:@"%@",[password stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    NSString *urlStr = [NSString stringWithFormat:@"%@Login?userName=%@&pwd=%@&userType=%d", SERVLET_URL, strForUrlOfuserName, strForUrlOfPassword, userType];
    NSURL *url = [NSURL URLWithString:urlStr];
    DLog(@"url is %@", url);
    
    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didLogin:) tag:tag];
    [opQueue addOperation:op];
    [op release];
}

-(void) signupWithUserName:  (NSString*)userName password: (NSString*)password email: (NSString*)email target: (id)target tag:(int)tag{
    NSString *urlStr = [NSString stringWithFormat:@"%@Signup?userName=%@&pwd=%@&email=%@", SERVLET_URL, userName, password, email];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didSignup:) tag:tag];
    [opQueue addOperation:op];
    [op release];
}

-(void) getImage: (NSString*)imageName target: (id)target tag:(int)tag {
    NSString *urlStr = [NSString stringWithFormat:@"%@%@", IMAGE_URL, imageName];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    ImageLoadingOperation *op = [[ImageLoadingOperation alloc] initWithImageURL:url target:target action:@selector(didGetImage:) tag:tag];
    [opQueue addOperation:op];
    [op release];
    
}

-(void) listKrowdsAtLatitude:(float)lat Longitude:(float)lng offset: (int)offset target:(id)target tag:(int)tag {
    NSString *urlStr = [NSString stringWithFormat:@"%@ListKrowds?lat=%f&lng=%f&offset=%d", SERVLET_URL, lat, lng, offset];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didListKrowds:) tag:tag];
    [opQueue addOperation:op];
    [op release];
}


-(void) join:(int)uid krowd:(int)krowdId supportingTeam:(int)teamId target:(id)target tag:(int)tag {
    NSString *urlStr = [NSString stringWithFormat:@"%@JoinKrowd?uid=%d&krowdId=%d&teamId=%d", SERVLET_URL, uid, krowdId, teamId];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didJoinKrowd:) tag:tag];
    [opQueue addOperation:op];
    [op release];
}


-(void) getAlbum: (int)uid target: (id)target tag:(int)tag {
    NSString *urlStr = [NSString stringWithFormat:@"%@GetAlbum?uid=%d", SERVLET_URL, uid];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didGetAlbum:) tag:tag];
    [opQueue addOperation:op];
    [op release];
}


-(void) recoverPasswordWithEmail:(NSString *)email target:(id)target tag:(int)tag {
    NSString *urlStr = [NSString stringWithFormat:@"%@RecoverPassword?email=%@", SERVLET_URL, email];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didRecoverPassword:) tag:tag];
    [opQueue addOperation:op];
    [op release];
}
//-(void) logOutWithUid: (int)uid target: (id)target tag:(int)tag {
//    NSString *urlStr = [NSString stringWithFormat:@"%Logout?uid=%@", SERVLET_URL, uid];
//    NSURL *url = [NSURL URLWithString:urlStr];
//    
//    TextLoadingOperation *op = [[TextLoadingOperation alloc] initWithTextURL:url target:target action:@selector(didlogOutWithUid:) tag:tag];
//    [opQueue addOperation:op];
//    [op release];
//}

-(void) subscribePush:(int)uid delegate:(id)delegate  {
    dataPushDelegate = delegate;
    cometConn = [[NSURLConnection alloc] initWithRequest:[NSURLRequest requestWithURL: [NSURL URLWithString:COMET_URL]] delegate:self];
    
}

-(void) getUserStatistics:(int)krowdId target:(id)target tag:(int)tag{
    
}
-(void) getUserAchievements:(int)krowdId target:(id)target tag:(int)tag{
    
}
-(void) getPosts: (int)krowdId offset :(int)offset target: (id)target tag:(int)tag{
    
    //init with fake data
}

-(void) getUserEvents:(int)krowdId target:(id)target tag:(int)tag{
    
}
-(void) getExtras:(int)krowdId target:(id)target tag:(int)tag{
    
}

#pragma mark -
#pragma mark delegate NSURLConnectionDelegate

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    DLog(@"did receive");
    [dataPushDelegate didPush:[[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding] autorelease]];
    
}

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
	DLog(@"didFail");
    [dataPushDelegate didPush: @"{\"error\":-1}"];
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    DLog(@"did finsih");
}

- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response {
    DLog(@"did received resp");
}

@end
