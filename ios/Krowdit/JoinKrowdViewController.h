//
//  JoinKrowdViewController.h
//  Krowdit
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PostOfficeProtocal.h"
#import "CellData.h"
#import "SingleCell.h"
#import "ImageView.h"


#define kCustomRowCount     7

@interface JoinKrowdViewController : UITableViewController {
    /** Define variable to show the cell and data*/
    SingleCell *tmpCell;
    NSArray *data;
    
    /**referring to xib-based UITableViewCell ('SingleCell') */
    UINib *cellNib;
    
    /** Define a Bool variable for
     controlling the tap of the cell*/
    BOOL flag;
    BOOL isChangeHeight;
    
   /**Define index to point the row  */ 
    NSIndexPath *now_selectedCellIndexPath;
    NSIndexPath *pre_selectedCellIndexPath;
    NSArray * krowdList;
    NSMutableArray *homeTeamLogo;
    NSMutableArray *awayTeamLogo;
    
    NSMutableDictionary *imageDownloadsInProgress;// the set of objects for each logo
    NSArray *entries;// the main data model for our UITableView
    NSIndexPath * indexPathForDisplayImage;
    

}

@property (nonatomic, retain) IBOutlet CellData *tmpCell;
@property (nonatomic, retain) NSArray *data;
@property (nonatomic, retain) UINib *cellNib;
@property (nonatomic) BOOL flag;
@property (nonatomic) BOOL isChangeHeight;
@property (nonatomic, retain) NSIndexPath *now_selectedCellIndexPath;
@property (nonatomic, retain) NSIndexPath *pre_selectedCellIndexPath;
@property (nonatomic, retain) NSMutableDictionary *imageDownloadsInProgress;



- (IBAction) goImage : (id) sender;
- (UIImage *) getImageByName : (NSString *)imaName;
- (void) btnClickLogout;
@end
