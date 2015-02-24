//
//  SettingViewControllers.m
//  Krowdit
//
//  Created by fujing on 7/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "SettingViewControllers.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "EC.h"


@implementation SettingViewControllers

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)dealloc
{
    [super dealloc];
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void) btnClickLogout{
    DLog(@"btnClickLogout");
    NSDictionary *myDictionary = [[NSDictionary alloc] init];
    NSArray *documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentDirectory = [documentPaths objectAtIndex:0];
    NSString * dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.plist"];
    [myDictionary writeToFile:dataFilePath atomically:YES];
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    [appDelegate backtousernav];
    
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.navigationItem.rightBarButtonItem = [[[UIBarButtonItem alloc] initWithTitle:@"Logout" style:UIBarButtonItemStylePlain target:self action:@selector(btnClickLogout)] autorelease];
//    self.tabBarController.customizableViewControllers = nil;
    self.navigationItem.hidesBackButton = YES;

}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (void)tabBarController:(UITabBarController *)tabBarController willBeginCustomizingViewControllers:(NSArray *)viewControllers{
    UIView *editView = [tabBarController.view.subviews objectAtIndex:1];
    editView.backgroundColor = [UIColor grayColor];
    UINavigationBar *modalNavBar = [editView.subviews objectAtIndex:0];
    //modalNavBar.tintColor = [UIColor orangeColor];
    modalNavBar.topItem.title = nil;
}

@end
