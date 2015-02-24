//
//  ProfileStatisticsViewController.h
//  Krowdit
//
//  Created by fujing on 7/22/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface ProfileStatisticsViewController : UIViewController {

    
}
@property (nonatomic, retain) IBOutlet UILabel *totalKrowdsJoined;
@property (nonatomic, retain) IBOutlet UILabel *largeKrowdJoined;
@property (nonatomic, retain) IBOutlet UILabel *textPostCount;
@property (nonatomic, retain) IBOutlet UILabel *picPostCount;
@property (nonatomic, retain) IBOutlet UILabel *largestKrowdMemberCount;
@property (nonatomic, retain) IBOutlet UILabel *favoriteKrowdType;
@property (nonatomic, retain) IBOutlet UILabel *favoriteLocation;



@end
