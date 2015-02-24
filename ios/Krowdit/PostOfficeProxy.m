//
//  PostOfficeProxy.m
//  Krowdit
//
//  Created by jacky on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "PostOfficeProxy.h"
#import "HTTPPostOffice.h"
#import "LocalPostOffice.h"

static PostOfficeProxy *_sharedPostOffice = nil;

@implementation PostOfficeProxy


#pragma mark -
#pragma mark delegate PostOfficeProtocal - reachability

-(BOOL)reachable {
    return [realPostOffice reachable];
}

#pragma mark -
#pragma mark delegate PostOfficeProtocal - synchorous


-(NSString*) loginWithUserName: (NSString*)userName password: (NSString*)password {
    return [realPostOffice loginWithUserName: userName password: password];
    
}


#pragma mark -
#pragma mark protocal PostOfficeProtocal

-(void) loginWithUserName: (NSString*)userName password: (NSString*)password userType: (int)userType target: (id)target tag:(int)tag{
    
    [realPostOffice loginWithUserName: userName password: password userType: userType target: target tag: tag];
}

-(void) signupWithUserName:  (NSString*)userName password: (NSString*)password email: (NSString*)email target: (id)target tag:(int)tag {
    [realPostOffice  signupWithUserName:  userName password: password email:email target: target tag:tag];
}

-(void) getImage: (NSString*)imageName target: (id)target tag:(int)tag  {
    [realPostOffice getImage: imageName target: target tag:tag];
}


-(void) listKrowdsAtLatitude:(float)lat Longitude:(float)lng offset: (int)offset target:(id)target tag:(int)tag {
    [realPostOffice listKrowdsAtLatitude:lat Longitude:lng offset:offset target:target tag:tag];
}


-(void) join:(int)uid krowd:(int)krowdId supportingTeam:(int)teamId target:(id)target tag:(int)tag {
    [realPostOffice join:uid krowd:krowdId supportingTeam:teamId target:target tag:tag];
}


-(void) getAlbum: (int)uid target: (id)target tag:(int)tag  {
    [realPostOffice getAlbum:uid target:target tag:tag];
}


-(void) recoverPasswordWithEmail:(NSString *)email target:(id)target tag:(int)tag {
    [realPostOffice recoverPasswordWithEmail:email target:target tag:tag];
}

//-(void) logOutWithUid: (int)uid target: (id)target tag:(int)tag {
//    [realPostOffice logOutWithUid:uid target:target tag:tag];
//}

-(void) subscribePush:(int)uid delegate:(id)delegate {
    [realPostOffice subscribePush:uid delegate:delegate];
}

-(void) getUserStatistics:(int)krowdId target:(id)target tag:(int)tag{
    [realPostOffice getUserStatistics:krowdId target:target tag:tag];
}

-(void) getUserAchievements:(int)krowdId target:(id)target tag:(int)tag{
    [realPostOffice getUserAchievements:krowdId target:target tag:tag];
}

-(void) getPosts: (int)krowdId offset :(int)offset target: (id)target tag:(int)tag {
    [realPostOffice getPosts:krowdId offset:offset target:target tag:tag];
}

-(void) getUserEvents:(int)krowdId target:(id)target tag:(int)tag{
    [realPostOffice getUserEvents:krowdId target:target tag:tag];
}

-(void) getExtras:(int)krowdId target:(id)target tag:(int)tag{
    [realPostOffice getExtras:krowdId target:target tag:tag];
}
#pragma mark -
#pragma mark singleton
/**
 get shared instance of proxy
 */
+ (PostOfficeProxy*)sharedPostOfficeProxy {
    //DLog(@"shared");
    @synchronized([PostOfficeProxy class]) {
        if (nil == _sharedPostOffice) {
            [[self alloc] init];
            
        }
    }
    
    //TODO potential leak
    return _sharedPostOffice;
}

+ (id)allocWithZone:(NSZone *)zone {
    //DLog(@"alloc zone");
    @synchronized([PostOfficeProxy class]) {
        if (nil == _sharedPostOffice) {
            _sharedPostOffice= [super allocWithZone:zone];

        }
    }
    return _sharedPostOffice;
}

+ (id) copyWithZone:(NSZone *)zone {
    @synchronized([PostOfficeProxy class]) {
        if (nil == _sharedPostOffice) {
            _sharedPostOffice= [super allocWithZone:zone];
 
        }
    }
    return _sharedPostOffice;
}

+ (void) attemptDealloc {
    if ([_sharedPostOffice retainCount] != 1)
        return;
    [_sharedPostOffice release];
    _sharedPostOffice = nil;
}

/**
 in the future, realPostOffice may also be a instance of SocketPostOffice
 */
- (id)init {
    //DLog(@"init");
    self = [super init];
    if (nil != self) {
        //realPostOffice = [[HTTPPostOffice alloc] init];
        realPostOffice = [[LocalPostOffice alloc] init];
    }
    
    return self;
    
}

- (id)copyWithZone:(NSZone *)zone {
    return self;
}

- (id)retain  
{  
    return self;  
}  

- (unsigned)retainCount  
{  
    return UINT_MAX;   
}  
//在上一个commit中，下面这个方法，为(void)release，提示警告，改为如下面的语句，警告消除，猜测为Xcode 版本原因
- (oneway void)release  
{  
    //This function should NOT do anything  
}  

- (id)autorelease  
{  
    return self;  
}  



@end
