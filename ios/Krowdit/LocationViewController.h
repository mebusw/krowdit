//
//  LocationViewController.h
//  Krowdit
//
//  Created by fujing on 7/18/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MapViewController.h"


@interface LocationViewController : UIViewController {
    
}
@property (nonatomic, retain) IBOutlet UILabel *homeTeamName;
@property (nonatomic, retain) IBOutlet UILabel *awayTeamName;
@property (nonatomic, retain) IBOutlet UILabel *homeSupportCount;
@property (nonatomic, retain) IBOutlet UILabel *awaySupportCount;
@property (nonatomic, retain) IBOutlet UILabel *fieldName;
@property (nonatomic, retain) IBOutlet UILabel *startTime;
@property (nonatomic, retain) IBOutlet UILabel *creatorName;
@property (nonatomic, retain) IBOutlet UILabel *memberCount;
@property (nonatomic, retain) IBOutlet UILabel *postCount;
@property (nonatomic, retain) IBOutlet UILabel *takenPicCount;
@property (nonatomic, retain) IBOutlet UIImageView *homeTeamImage;
@property (nonatomic, retain) IBOutlet UIImageView *awayTeamImage;

-(IBAction) goMap:(id)sender;
- (void)goBackKrowd;

@end
