//
//  CameraViewController.h
//  Krowdit
//
//  Created by jacky on 7/18/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol CameraProtocal <NSObject>

-(void) didPickingImage: (UIImage*) image;

@end

@interface CameraViewController : UIViewController <UIImagePickerControllerDelegate, UINavigationControllerDelegate> {
    
}

@property (nonatomic, retain) IBOutlet UIImageView *imageView;

/** to be passed back to delegate, who calls this CameraViewController */
@property (nonatomic, retain) UIImage *pickedImage;
@property (nonatomic, retain) id<CameraProtocal> delegate;

-(IBAction) btnPickAPhoto;
-(IBAction) btnTakeAShot;
-(IBAction) btnCancel;

@end
