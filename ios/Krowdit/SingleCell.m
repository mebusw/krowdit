//
//  SingleCell.m
//  Krowdit
//
//  Created by fujing on 6/21/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "SingleCell.h"

/** Implement the function which can show the
 background color and receive data*/
@implementation SingleCell
@synthesize locationLabel;
@synthesize creatorLabel;
@synthesize createTimeLabel;
@synthesize creatorNameLabel;
@synthesize createDateLabel;
@synthesize nameLabel;
@synthesize awayNameLabel;
@synthesize placeNameLabel;
@synthesize typeNameLabel;
@synthesize sizeLabel;
@synthesize homeSupportLabel;
@synthesize awaySupportLabel;
@synthesize timeLabel;
@synthesize showMessageLabel;
@synthesize iconView;
@synthesize iconView1;
@synthesize iconView2;

@synthesize btnAway;
@synthesize btnHome;

- (void)setBackgroundColor:(UIColor *)backgroundColor
{
    [super setBackgroundColor:backgroundColor];
    
    
    locationLabel.backgroundColor = backgroundColor;
    creatorLabel.backgroundColor = backgroundColor;
    createTimeLabel.backgroundColor = backgroundColor;
    creatorNameLabel.backgroundColor = backgroundColor;
    createDateLabel.backgroundColor = backgroundColor;
    nameLabel.backgroundColor = backgroundColor;
    awayNameLabel.backgroundColor = backgroundColor;
    placeNameLabel.backgroundColor = backgroundColor;
    typeNameLabel.backgroundColor = backgroundColor;
    sizeLabel.backgroundColor = backgroundColor;
    homeSupportLabel.backgroundColor = backgroundColor;
    awaySupportLabel.backgroundColor = backgroundColor;
    timeLabel.backgroundColor = backgroundColor;
}

- (void)setLocation:(NSString *)newLocation{
    [super setLocation:newLocation];
    locationLabel.text=newLocation;
}
- (void)setCreator:(NSString *)newCreator{
    [super setCreator:newCreator];
    creatorLabel.text = newCreator;
}
- (void)setCreateTime:(NSString *)newCreateTime{
    [super setCreateTime:newCreateTime];
    createTimeLabel.text = newCreateTime;
}
- (void)setSize:(NSInteger)newSize{
    [super setSize:newSize];
    sizeLabel.text=[NSString stringWithFormat:@"%d", newSize];
}
- (void)setHomeSupportNumber:(NSInteger)newHomeSupporterNumber{
    [super setHomeSupportNumber:newHomeSupporterNumber];
    homeSupportLabel.text = [NSString stringWithFormat:@"%d",newHomeSupporterNumber];
}
- (void)setAwaySupportNumber:(NSInteger)newAwaySupportNumber{
    [super setAwaySupportNumber:newAwaySupportNumber];
    awaySupportLabel.text = [NSString stringWithFormat:@"%d",newAwaySupportNumber];
}
- (void)setCreatorName:(NSString *)newCreatorName{
    [super setCreatorName:newCreatorName];
    creatorNameLabel.text = newCreatorName;
}
- (void)setCreateDate:(NSString *)newCreateDate{
    [super setCreateDate:newCreateDate];
    createDateLabel.text = newCreateDate;
}
- (void)setName:(NSString *)newName{
    [super setName:newName];
    nameLabel.text=newName;
}
- (void)setAwayName:(NSString *)newAwayname{
    [super setAwayName:newAwayname];
    awayNameLabel.text=newAwayname;
}
- (void)setPlaceName:(NSString *)newPlaceName{
    [super setPlaceName:newPlaceName];
    placeNameLabel.text=newPlaceName;
}
- (void)setTypeName:(NSString *)newTypeName{
    [super setTypeName:newTypeName];
    typeNameLabel.text=newTypeName;
}
- (void)setTime:(NSString *)newTime{
    [super setTime:newTime];
    timeLabel.text=newTime;
}
- (void)setShowMessage:(NSString *)newShowMessage{
    [super setShowMessage:newShowMessage];
    showMessageLabel.text = newShowMessage;
}
- (void) setIcon:(UIImage *)newIcon{
    [super setIcon:newIcon];
    iconView.image=newIcon;
}
- (void) setIcon1:(UIImage *)newIcon1{
    [super setIcon1:newIcon1];
    iconView1.image=newIcon1;
}

- (void) setIcon2:(UIImage *)newIcon2{
    [super setIcon1:newIcon2];
    iconView2.image=newIcon2;
}

- (void)dealloc
{
    
    [locationLabel release];
    [creatorLabel release];
    [createTimeLabel release];
    [nameLabel release];
    [creatorNameLabel release];
    [createDateLabel release];
    [awayNameLabel release];
    [placeNameLabel release];
    [typeNameLabel release];
    [sizeLabel release];
    [homeSupportLabel release];
    [awaySupportLabel release];
    [timeLabel release];
    [showMessageLabel release];
    [iconView release];
    [iconView1 release];
    [iconView2 release];
    
    [super dealloc];
}


@end
