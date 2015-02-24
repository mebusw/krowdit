//
//  BasicKrowdViewController.h
//  Krowdit
//
//  Created by Jia Yong on 7/15/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BasicKrowdViewController : UITableViewController{
    
    NSMutableArray *list;
    NSString * dataFilePath;
    
}

@property (nonatomic,retain) NSMutableArray *list;
@end
