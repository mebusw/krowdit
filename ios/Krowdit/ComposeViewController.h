//
//  ComposeViewController.h
//  Krowdit
//
//  Created by Jia Yong on 7/20/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ComposeViewController : UIViewController{

    UITextView * mytextView;
    NSString * str;
    NSDictionary *dictForString;
    NSMutableArray * list;
    NSString * dataFilePath;

}

@property(nonatomic,retain) IBOutlet UITextView * mytextView;

- (IBAction)send:(id)sender;
- (IBAction)photos:(id)sender;
- (IBAction)cancel:(id)sender;
@end
