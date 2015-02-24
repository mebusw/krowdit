//
//  KrowdBean.h
//  Krowdit
//
//  Created by jacky on 7/15/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 as a global variant, stores info of currently joined krowd
 */
@interface KrowdBean : NSObject {
    
}

@property (nonatomic) NSInteger krowdId;
@property (nonatomic) NSInteger homeTeamId;
@property (nonatomic) NSInteger awayTeamId;
@property (nonatomic) NSInteger supportingTeamId;
@property (nonatomic) NSInteger locationId;

@property (nonatomic, retain) NSString *locationName;
@property (nonatomic, retain) NSString *homeTeamName;
@property (nonatomic, retain) NSString *awayTeamName;
@property (nonatomic, retain) NSString *creatorName;
@property (nonatomic, retain) UIImageView *homeTeamLogo;
@property (nonatomic, retain) UIImageView *awayTeamLogo;
@property (nonatomic, retain) NSDate *startTime;

@property (nonatomic) NSInteger postCount;
@property (nonatomic) NSInteger takenPicCount;
@property (nonatomic) NSInteger memberCount;

@end
