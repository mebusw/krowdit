//
//  PostOfficeProtocal.h
//  Krowdit
//
//  Created by jacky on 6/9/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

/** 
 Post Office is the communication module between iPhone client and server.
 The request & response parameter conform to http://192.168.1.113/mediawiki/index.php/Sever_Error_Code
 
 @see DataPullDelegate
 @see DataPushDelegate
 */
@protocol PostOfficeProtocal

#pragma mark -
#pragma mark synchronous reachability

/**
 Detect the server's reachability.
 @return server reachable or not
 
 */
-(BOOL)reachable;


#pragma mark -
#pragma mark synchronous requests


/**
 login request synchorously
 @param userName user name
 @param password password
 */
-(NSString*) loginWithUserName: (NSString*)userName password: (NSString*)password;


#pragma mark -
#pragma mark asynchronous requests

/**
 the login request
 @param userName user name
 @param password password
 @param userType type of user, either krowdit user or facebook user
 @param target the delegate object. The callback -didLogin: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) loginWithUserName: (NSString*)userName password: (NSString*)password userType: (int)userType target: (id)target tag:(int)tag;


/**
 the signup request
 @param userName user name
 @param password password
 @param email email
 @param target the delegate object. The callback -didSignup: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) signupWithUserName: (NSString*)userName password: (NSString*)password email: (NSString*)email target: (id)target tag:(int)tag;


/**
 get an image with imageId
 @param imageName the name of image
 @param target the delegate object. The callback -didGetImage: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) getImage: (NSString*) imageName target: (id)target tag: (int)tag;


/**
 list krowds at given coordinates
 @param lat latitude of user
 @param lng longitude of user
 @param offset the start offset for page splitting
 @param target the delegate object. The callback -didListKrowds: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) listKrowdsAtLatitude: (float)lat Longitude: (float)lng offset: (int)offset target: (id)target tag:(int)tag;


/**
 join a Krowd and support a team of that
 @param uid id of user
 @param krowdId id of krowd
 @param teamId id of supporting team
 @param target the delegate object. The callback -didJoinKrowd: defined in DataPullDelegate 
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) join: (int)uid krowd: (int)krowdId supportingTeam: (int)teamId target: (id)target tag:(int)tag;


/**
 get all images in album of a user
 @param uid id of user
 @param target the delegate object. The callback -didGetAlbum: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) getAlbum: (int)uid target: (id)target tag:(int)tag;

/**
 Recovery / Reset password for user with his registered email, the new password will be sent to that email.
 @param email user's email, to which the new password will be sent 
 @param target the delegate object. The callback -didRecoverPwd: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
-(void) recoverPasswordWithEmail: (NSString*)email target: (id)target tag:(int)tag;

/**
 get Statistics of a krowd /
 @param krowdId id of krowd 
 */
-(void) getUserStatistics: (int)krowdId target: (id)target tag:(int)tag;

/**
 get Achievements of a krowd /
 @param krowdId id of krowd 
 */
-(void) getUserAchievements: (int)krowdId target: (id)target tag:(int)tag;

/**
 get Posts of a krowd /
 @param krowdId id of krowd 
 @param offset the start offset for page splitting

 */
-(void) getPosts: (int)krowdId offset :(int)offset target: (id)target tag:(int)tag;

/** 
 get Event Statics of a krowd
 @param kroedId id of krowd
 
 */
-(void) getUserEvents: (int)krowdId target:(id) target tag:(int)tag;

/**
 get Extras of a krowd /
 @param krowdId id of krowd 
 */
-(void) getExtras: (int)krowdId target: (id)target tag:(int)tag;


/**
 logOut
 @param uid user's id, get from server when login successfully. 
 @param target the delegate object. The callback -didRecoverPwd: defined in DataPullDelegate
 @param tag to identified if there're several asyn requests at the same time
 @see DataPullDelegate
 
 */
//-(void) logOutWithUid: (int)uid target: (id)target tag:(int)tag;

#pragma mark -
#pragma mark push by comet connection

/**
 subscribe the notifications pushed from server
 @param uid the uese id
 @param target the delegate object. The callback -didPush: defined in DataPushDelegate
 @see DataPushDelegate
 
 */
-(void) subscribePush: (int)uid delegate: (id)delegate;


@end


#pragma mark -

/** 
 DataPullDelegate defines the callback functions for asyn requests by Post Office.
 All the callbacks are optional, which will be invoked while server response.
 
 @see PostOfficeProtocal
 */
@protocol DataPullDelegate 

@optional

/**
 callback when server did response for login request
 @param string JSON string
 */
- (void) didLogin: (NSString *)string;


/**
 callback when server did response for signup request
 @param string JSON string
 */
- (void) didSignup: (NSString *)string;


/**
 callback when server did response for getImage request
 @param obj A NSDictionary with two keys: "image" and "json"
 */
- (void) didGetImage: (NSDictionary *) dict;


/**
 callback when server did response for listKrowds request
 @param string JSON string
 */
- (void) didListKrowds: (NSString *)string;


/**
 callback when server did response for joinKrowd request
 @param string JSON string
 */
- (void) didJoinKrowd: (NSString *)string;


/**
 callback when server did response for getAlbum request
 @param string JSON string
 */
- (void) didGetAlbum: (NSString *)string;


/**
 callback when server did response for RecoverPassword request
 @param string JSON string
 */
- (void) didRecoverPassword: (NSString *)string;

/**
 callback when server did response for logOut request
 @param string JSON string
 */
//- (void) didlogOutWithUid: (NSString *)string;

/**
 callback when server did response for getUserStatistics request
 @param string JSON string
 */
- (void) didGetUserStatistics: (NSString *)string;

/**
 callback when server did response for getUserAchievements request
 @param string JSON string
 */
- (void) didGetUserAchievements: (NSString *)string;

/**
 callback when server did response for getPosts request
 @param string JSON string
 */
- (void) didGetPosts: (NSString *)string;

/**
 callback when server did response for getUserEvents request
 @param string JSON string
 */
- (void) didGetUserEvents: (NSString *)string;

/**
 callback when server did response for getExtras request
 @param string JSON string
 */
- (void) didGetExtras: (NSString *)string;



@end


#pragma mark -

/** 
 DataPushDelegate defines the callback functions for asyn requests by Post Office.
 All the callbacks are optional, which will be invoked while server push anything.
 
 @see PostOfficeProtocal
 */
@protocol DataPushDelegate 

/**
 callback when server did pushed anything through comet connection
 @param string JSON string
 */
- (void)didPush: (NSString *)string;


@end
