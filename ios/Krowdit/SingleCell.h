//
//  SingleCell.h
//  Krowdit
//
//  Created by fujing on 6/21/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "CellData.h"


/** This file define some label variables
 which is used to show the receiving
 data*/

@interface SingleCell : CellData {
        
    IBOutlet UILabel *locationLabel;
    IBOutlet UILabel *creatorLabel;
    IBOutlet UILabel *createTimeLabel;
    IBOutlet UILabel *creatorNameLabel;
    IBOutlet UILabel *createDateLabel;
    IBOutlet UILabel *nameLabel;
    IBOutlet UILabel *awayNameLabel;
    IBOutlet UILabel *placeNameLabel;
    IBOutlet UILabel *typeNameLabel;
    IBOutlet UILabel *sizeLabel;
    IBOutlet UILabel *homeSupportLabel;
    IBOutlet UILabel *awaySupportLabel;
    IBOutlet UILabel *timeLabel;
    IBOutlet UILabel *showMessageLabel;
    IBOutlet UIImageView *iconView;
    IBOutlet UIImageView *iconView1;
    IBOutlet UIImageView *iconView2;
    
    IBOutlet UIButton *btnHome;
    IBOutlet UIButton *btnAway;
    
}

@property (nonatomic, retain) UILabel *locationLabel;
@property (nonatomic, retain) UILabel *creatorLabel;
@property (nonatomic, retain) UILabel *createTimeLabel;
@property (nonatomic, retain) UILabel *creatorNameLabel;
@property (nonatomic, retain) UILabel *createDateLabel;
@property (nonatomic, retain) UILabel *nameLabel;
@property (nonatomic, retain) UILabel *awayNameLabel;
@property (nonatomic, retain) UILabel *placeNameLabel;
@property (nonatomic, retain) UILabel *typeNameLabel;
@property (nonatomic, retain) UILabel *sizeLabel;
@property (nonatomic, retain) UILabel *homeSupportLabel;
@property (nonatomic, retain) UILabel *awaySupportLabel;
@property (nonatomic, retain) UILabel *timeLabel;
@property (nonatomic, retain) UILabel *showMessageLabel;
@property (nonatomic, retain) UIImageView *iconView;
@property (nonatomic, retain) UIImageView *iconView1;
@property (nonatomic, retain) UIImageView *iconView2;

@property (nonatomic, retain) UIButton *btnHome;
@property (nonatomic, retain) UIButton *btnAway;


@end
