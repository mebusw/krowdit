//
//  CellData.m
//  Krowdit
//
//  Created by fujing on 6/21/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "CellData.h"

/** This file just synthesize and release defined data*/
@implementation CellData

@synthesize location;
@synthesize creator;
@synthesize createTime;
@synthesize creatorName;
@synthesize createDate;
@synthesize name;
@synthesize awayName;
@synthesize placeName;
@synthesize typeName;
@synthesize size;
@synthesize homeSupportNumber;
@synthesize awaySupportNumber;
@synthesize time;
@synthesize showMessage;
@synthesize icon;
@synthesize icon1;
@synthesize icon2;

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (void)dealloc
{
    [location release];
    [creator release];
    [createTime release];
    [creatorName release];
    [createDate release];
    [name release];
    [awayName release];
    [placeName release];
    [typeName release];
    [time release];
    [showMessage release];
    [icon release];
    [icon1 release];
    [icon2 release];
    
    [super dealloc];
}

@end
