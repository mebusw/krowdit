//
//  Welcome.h
//  Krowdit
//
//  Created by jacky on 7/13/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CameraViewController.h"
#import "ComposeViewController.h"

@interface WelcomeViewController : UIViewController <CameraProtocal> {
    ComposeViewController *composeViewController;
}

@property (nonatomic, retain) IBOutlet UILabel *welcomeUsername;
@property (nonatomic, retain) IBOutlet UILabel *welcomeLocation;
@property (nonatomic, retain) IBOutlet UIImageView *imageView;
@property (nonatomic, retain) IBOutlet ComposeViewController *composeViewController;

-(IBAction) clickBtnPostScreen;
-(IBAction) clickBtnKrowdScreen;
-(IBAction) clickBtnCameraScreen;

@end
