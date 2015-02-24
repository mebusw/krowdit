//
//  CellData.h
//  Krowdit
//
//  Created by fujing on 6/21/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

/** Define some basic data in the cell*/

@interface CellData : UITableViewCell {
    
    NSString *location;
    NSString *creator;
    NSString *createTime;
    NSString *creatorName;
    NSString *createDate;
    NSString *name;
    NSString *awayName;
    NSString *placeName;
    NSString *typeName;
    NSInteger homeSupportNumber;
    NSInteger awaySupportNumber;
    NSInteger size;
    NSString *time;
    NSString *showMessage;
    UIImage *icon;
    UIImage *icon1;
    UIImage *icon2;
    
}

@property (retain) NSString *location;
@property (retain) NSString *creator;
@property (retain) NSString *createTime;
@property (retain) NSString *creatorName;
@property (retain) NSString *createDate;
@property (retain) NSString *name;
@property (retain) NSString *awayName;
@property (retain) NSString *placeName;
@property (retain) NSString *typeName;
@property NSInteger size;
@property NSInteger homeSupportNumber;
@property NSInteger awaySupportNumber;
@property (retain) NSString *time;
@property (retain) NSString *showMessage;
@property (retain) UIImage *icon;
@property (retain) UIImage *icon1;
@property (retain) UIImage *icon2;

@end
