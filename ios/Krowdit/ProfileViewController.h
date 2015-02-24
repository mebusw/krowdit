//
//  ProfileViewController.h
//  Krowdit
//
//  Created by jacky on 7/18/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface ProfileViewController : UIViewController {
    
}

@property (nonatomic, retain) IBOutlet UILabel *lbUserName;
@property (nonatomic, retain) IBOutlet UIImageView *imageView;

-(IBAction) btnStatistics;
-(IBAction) btnAchievements;
-(IBAction) btnPhotos;
-(IBAction) btnEvents;

@end
