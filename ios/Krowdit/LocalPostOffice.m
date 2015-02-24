//
//  LocalPostOffice.m
//  Krowdit
//
//  Created by jacky on 7/14/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "LocalPostOffice.h"
#import "SBJson.h"


@implementation LocalPostOffice


#pragma mark -
#pragma mark delegate PostOfficeProtocal - reachability

-(BOOL)reachable {
    return YES;
}

#pragma mark -
#pragma mark delegate PostOfficeProtocal - synchorous


-(NSString*) loginWithUserName: (NSString*)userName password: (NSString*)password {
    return nil;
}

#pragma mark -
#pragma mark delegate PostOfficeProtocal - asynchorous

-(void) loginWithUserName: (NSString*)userName password: (NSString*)password userType: (int)userType target: (id)target tag:(int)tag {
    NSString *jsonStr = [NSString stringWithFormat: @"{\"error\":%d, \"uid\":1, \"tag\":%d, \"imageName\":\"%@\"}", SUCCESS, tag, @"garfield.jpg"];
    [target performSelector:@selector(didLogin:) withObject:jsonStr];
}

-(void) signupWithUserName:  (NSString*)userName password: (NSString*)password email: (NSString*)email target: (id)target tag:(int)tag{
    NSString *jsonStr = [NSString stringWithFormat: @"{\"error\":%d, \"uid\":1, \"tag\":%d}", SUCCESS, tag];
    [target performSelector:@selector(didSignup:) withObject:jsonStr];
}

-(void) getImage: (NSString*)imageName target: (id)target tag:(int)tag {
    NSString *jsonStr = [NSString stringWithFormat: @"{\"error\":%d, \"uid\":1, \"tag\":%d}", SUCCESS, tag];

    UIImage *image = [UIImage imageNamed: imageName];
    //DLog(@"imagePath=%@ ", imageName, image);

    
    NSDictionary *dict = [NSDictionary dictionaryWithObjectsAndKeys:jsonStr, @"json", image, @"image", nil];
    [target performSelector:@selector(didGetImage:) withObject:dict];
 
}

-(void) listKrowdsAtLatitude:(float)lat Longitude:(float)lng offset: (int)offset target:(id)target tag:(int)tag {
    NSString *jsonStr = [NSString stringWithFormat: @"{\"krowds\":[{\"createTime\":\"06-24-2011 15:45:49\",\"awayTeamLogo\":\"Arsenal.png\",\"creatorId\":1,\"lng\":10.0,\"locationName\":\"San Siro Stadium\",\"startTime\":\"07-14-2011 16:00:00\",\"awaySupporterCount\":20,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"NFL\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":100,\"lat\":9.98,\"krowdId\":1}, {\"createTime\":\"06-28-2011 15:45:49\",\"awayTeamLogo\":\"Bayern.png\",\"creatorId\":1,\"lng\":10.0,\"locationName\":\"Tianjin\",\"startTime\":\"07-30-2011 16:00:00\",\"awaySupporterCount\":50,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":11,\"krowdTypeName\":\"NFL\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":10,\"awayTeamName\":\"Bayern\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":150,\"lat\":9.98,\"krowdId\":2},{\"createTime\":\"06-24-2011 15:45:49\",\"awayTeamLogo\":\"Arsenal.png\",\"creatorId\":3,\"lng\":10.0,\"locationName\":\"San Siro Stadium\",\"startTime\":\"08-01-2011 19:00:00\",\"awaySupporterCount\":20,\"creatorName\":\"fujing\",\"awayTeamId\":2,\"krowdTypeName\":\"CCA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"BeiShang\",\"homeTeamName\":\"TianJin\",\"homeSupporterCount\":80,\"lat\":9.98,\"krowdId\":1}, {\"createTime\":\"07-28-2011 15:45:49\",\"awayTeamLogo\":\"Bayern.png\",\"creatorId\":2,\"lng\":10.0,\"locationName\":\"BeiJing\",\"startTime\":\"07-31-2011 20:00:00\",\"awaySupporterCount\":40,\"creatorName\":\"Cindy\",\"awayTeamId\":11,\"krowdTypeName\":\"ASA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":10,\"awayTeamName\":\"HangZhou\",\"homeTeamName\":\"ShangHai\",\"homeSupporterCount\":160,\"lat\":9.98,\"krowdId\":2},{\"createTime\":\"06-24-2011 15:45:49\",\"awayTeamLogo\":\"Arsenal.png\",\"creatorId\":4,\"lng\":10.0,\"locationName\":\"San Siro Stadium\",\"startTime\":\"08-01-2011 17:00:00\",\"awaySupporterCount\":25,\"creatorName\":\"JYong\",\"awayTeamId\":2,\"krowdTypeName\":\"NFL\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"BerBa\",\"homeSupporterCount\":105,\"lat\":9.98,\"krowdId\":1}, {\"createTime\":\"06-28-2011 15:45:49\",\"awayTeamLogo\":\"Bayern.png\",\"creatorId\":0,\"lng\":10.0,\"locationName\":\"ShangHai\",\"startTime\":\"08-06-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"Sam\",\"awayTeamId\":11,\"krowdTypeName\":\"NFL\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":10,\"awayTeamName\":\"Real Madrid\",\"homeTeamName\":\"TianJin\",\"homeSupporterCount\":10,\"lat\":9.98,\"krowdId\":2}],\"error\":%d,\"serverTime\":\"07-14-2011 16:49:24\",\"lng\":10.0,\"lat\":10.0, \"tag\":%d}", SUCCESS, tag];
    [target performSelector:@selector(didListKrowds:) withObject:jsonStr];

}


-(void) join:(int)uid krowd:(int)krowdId supportingTeam:(int)teamId target:(id)target tag:(int)tag {
    NSString *jsonStr = [NSString stringWithFormat: @"{\"error\":%d, \"postCount\":100, \"takenPicCount\":200, \"memberCount\":300, \"tag\":%d}", SUCCESS, tag];
    [target performSelector:@selector(didJoinKrowd:) withObject:jsonStr];
}


-(void) getAlbum: (int)uid target: (id)target tag:(int)tag {
    [target performSelector:@selector(didGetAlbum:) withObject:nil];
}


-(void) recoverPasswordWithEmail:(NSString *)email target:(id)target tag:(int)tag {
    NSString *jsonStr = [NSString stringWithFormat: @"{\"error\":%d, \"tag\":%d}", SUCCESS, tag];
    [target performSelector:@selector(didRecoverPassword:) withObject:jsonStr];
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

}
-(void) getPosts: (int)krowdId offset :(int)offset target: (id)target tag:(int)tag{
   
    //init with fake data
    NSDictionary * dict1 = [NSDictionary dictionaryWithObjectsAndKeys:@"MJ",@"mainTitleKey", @"GO!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"2",@"teamId",nil];
    NSDictionary * dict2 = [NSDictionary dictionaryWithObjectsAndKeys:@"Pete",@"mainTitleKey", @"Yeah, u r only down 9 points now!",@"secondaryTitleKey",@"Pete_Logo",@"imageKey",@"1",@"teamId",nil];
    NSDictionary * dict3 = [NSDictionary dictionaryWithObjectsAndKeys:@"Dwight",@"mainTitleKey", @"MJ,disregard Pete.Game is on and he is forgetful!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"2",@"teamId",nil];
    NSDictionary * dict4 = [NSDictionary dictionaryWithObjectsAndKeys:@"Pam",@"mainTitleKey", @"Here's to Pete at the CB's!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"1",@"teamId",nil];
    NSDictionary * dict5 = [NSDictionary dictionaryWithObjectsAndKeys:@"Cindy",@"mainTitleKey", @"GO!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"1",@"teamId",nil];
    NSDictionary * dict6 = [NSDictionary dictionaryWithObjectsAndKeys:@"Ryan",@"mainTitleKey", @"GO!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"1",@"teamId",nil];    
    NSMutableArray * list = [NSMutableArray arrayWithObjects:dict1,dict2,dict3,dict4,dict5,dict6,nil];
    NSString * strtag = [NSString stringWithFormat:@"%d",tag];
    NSString * strerror = [NSString stringWithFormat:@"%d",SUCCESS];
    NSDictionary * dictForPost = [NSDictionary dictionaryWithObjectsAndKeys:list,@"posts",strerror,@"error",strtag,@"tag",nil];
    SBJsonWriter * swtem = [[SBJsonWriter alloc] init];
    NSString * jsonStr = [swtem stringWithObject:dictForPost];
    [target performSelector:@selector(didGetPosts:) withObject:jsonStr];
    [swtem release];

}
-(void)getUserAchievements:(int)krowdId target:(id)target tag:(int)tag{
    NSString *jsonStr = [NSString stringWithFormat: @"{\"Achievements\":[{\"name\":\"Founding Father\",\"image\":\"FF.png\",\"count\":\"(x5)\",\"detail\":\"Found a Krowd that grows to at least 25 members\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1},{\"name\":\"Early Bird\",\"image\":\"EB.png\",\"count\":\"(x2)\",\"detail\":\"Be one of the first 10 people in a Krowd that grows to at least 25 members\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1},{\"name\":\"Underdog\",\"image\":\"UD.png\",\"count\":\"\",\"detail\":\"Be a member of a Krowd that is outnumbered five to onr by the opposing team's Krowd\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1}],\"error\":%d,\"serverTime\":\"07-14-2011 16:49:24\",\"lng\":10.0,\"lat\":10.0, \"tag\":%d}", SUCCESS, tag];
    
    [target performSelector:@selector(didGetUserAchievements:) withObject:jsonStr];
}

-(void)getUserStatistics:(int)krowdId target:(id)target tag:(int)tag{
     NSString *jsonStr = [NSString stringWithFormat:@"{\"createTime\":\"06-24-2011 15:45:49\",\"awayTeamLogo\":\"Arsenal.png\",\"totalKrowdsJoined\":100,\"textPostCount\":213,\"favoriteLocation\":\"San Siro Stadium\",\"picPostCount\":\"24\",\"LargeKrowdJoined\":20,\"creatorName\":\"KrowditStaff\",\"largestKrowdMemberCount\":6000,\"favoriteKrowdType\":\"NFL Football\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1,\"error\":%d,\"serverTime\":\"07-14-2011 16:49:24\",\"lng\":10.0,\"lat\":10.0, \"tag\":%d}",SUCCESS,tag];
    [target performSelector:@selector(didGetUserStatistics:) withObject:jsonStr];
}
-(void)getUserEvents:(int)krowdId target:(id)target tag:(int)tag{
    NSString *jsonStr = [NSString stringWithFormat: @"{\"krowds\":[{\"createTime\":\"06-24-2011 15:45:49\",\"awayTeamLogo\":\"Arsenal.png\",\"creatorId\":1,\"lng\":10.0,\"locationName\":\"San Siro Stadium\",\"startTime\":\"07-14-2011 16:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1}, {\"createTime\":\"06-28-2011 15:45:49\",\"awayTeamLogo\":\"Bayern.png\",\"creatorId\":1,\"lng\":10.0,\"locationName\":\"Tianjin\",\"startTime\":\"07-14-2011 16:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":11,\"krowdTypeName\":\"NFL\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":10,\"awayTeamName\":\"Bayern\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":2},{\"createTime\":\"06-24-2011 15:45:49\",\"awayTeamLogo\":\"Arsenal.png\",\"creatorId\":1,\"lng\":10.0,\"locationName\":\"San Siro Stadium\",\"startTime\":\"07-14-2011 16:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"NBA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"C-Bulls\",\"homeTeamName\":\"H-Rocket\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1}, {\"createTime\":\"06-28-2011 15:45:49\",\"awayTeamLogo\":\"Bayern.png\",\"creatorId\":1,\"lng\":10.0,\"locationName\":\"Tianjin\",\"startTime\":\"07-14-2011 16:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":11,\"krowdTypeName\":\"CBA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":10,\"awayTeamName\":\"BeiJing\",\"homeTeamName\":\"TianJin\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":2}],\"error\":%d,\"serverTime\":\"07-14-2011 16:49:24\",\"lng\":10.0,\"lat\":10.0, \"tag\":%d}", SUCCESS, tag];
    [target performSelector:@selector(didGetUserEvents:) withObject:jsonStr];
    
    
}
-(void) getExtras:(int)krowdId target:(id)target tag:(int)tag{
    NSString *jsonStr = [NSString stringWithFormat: @"{\"Extras\":[{\"name\":\"Flash Team Colors\",\"image\":\"FTC.png\",\"count\":\"(*5)\",\"detail\":\"Found a Krowd that grows to at least 25 members\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1},{\"name\":\"Beer Me #1\",\"image\":\"BM1.png\",\"count\":\"(*2)\",\"detail\":\"Be one of the first 10 people in a Krowd that grows to at least 25 members\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1},{\"name\":\"Beer Me #2\",\"image\":\"BM2.png\",\"count\":\"\",\"detail\":\"Be a member of a Krowd that is outnumbered five to onr by the opposing team's Krowd\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1},{\"name\":\"Strobe Light\",\"image\":\"SL.png\",\"count\":\"(*5)\",\"detail\":\"Found a Krowd that grows to at least 25 members\",\"startTime\":\"07-30-2011 19:00:00\",\"awaySupporterCount\":2,\"creatorName\":\"KrowditStaff\",\"awayTeamId\":2,\"krowdTypeName\":\"FIFA\",\"homeTeamLogo\":\"Barcelona.png\",\"krowdTypeId\":1,\"homeTeamId\":1,\"awayTeamName\":\"Arsenal\",\"homeTeamName\":\"Barcelona\",\"homeSupporterCount\":1,\"lat\":9.98,\"krowdId\":1}],\"error\":%d,\"serverTime\":\"07-14-2011 16:49:24\",\"lng\":10.0,\"lat\":10.0, \"tag\":%d}", SUCCESS, tag];
    
    [target performSelector:@selector(didGetExtras:) withObject:jsonStr];
    
}

@end
