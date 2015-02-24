//
//  ProfileViewController.m
//  Krowdit
//
//  Created by jacky on 7/18/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "ProfileViewController.h"
#import "PostOfficeProxy.h"
#import "KrowditAppDelegate.h"
#import "ProfileAchievementsViewController.h"
#import "ProfileEventsViewController.h"
#import "ProfileStatisticsViewController.h"
#import "ProfilePhotosViewController.h"

@implementation ProfileViewController

@synthesize lbUserName;
@synthesize imageView;


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

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    self.title = @"Profile";
    self.navigationItem.rightBarButtonItem = [[[UIBarButtonItem alloc] initWithTitle:@"LogOut" style:UIBarButtonItemStylePlain target:self action:@selector(btnClickLogout)] autorelease];
    
}

- (void) btnClickLogout{
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    appDelegate.userBean.userId = -1;
    appDelegate.krowdBean.krowdId = -1;
    appDelegate.krowdBean.locationId = -1;
    appDelegate.krowdBean.locationName = @"";
    [appDelegate backtousernav];

}

- (void) didGetImage: (NSDictionary *) dict {
    NSDictionary *json = [[dict  objectForKey:@"json"] JSONValue];
    if (SUCCESS == [[json objectForKey:@"error"] intValue]) {
        UIImage * img = [dict objectForKey:@"image"];
        self.imageView.image = img;
    }
}

- (void) viewWillAppear:(BOOL)animated {
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    lbUserName.text = appDelegate.userBean.userName;
    
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po getImage:appDelegate.userBean.imageName target:self tag:0];
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

#pragma mark - Actions

-(IBAction) btnStatistics {
    ProfileStatisticsViewController *psvc = [[ProfileStatisticsViewController alloc] init];
    [self.navigationController pushViewController:psvc animated:YES];
    
    
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] init];
    backItem.title=@"Back";
    [self.navigationItem setBackBarButtonItem:backItem];
    
    [backItem release];
    [psvc release];
    
}

-(IBAction) btnAchievements {
    ProfileAchievementsViewController *pavc = [[ProfileAchievementsViewController alloc] init];
    [self.navigationController pushViewController:pavc animated:YES];
    
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] init];
    backItem.title=@"Back";
    [self.navigationItem setBackBarButtonItem:backItem];
    
    [backItem release];
    [pavc release];


    
}

-(IBAction) btnPhotos {
    ProfilePhotosViewController * profilePhotos = [[ProfilePhotosViewController alloc] init];
    [self.navigationController pushViewController:profilePhotos animated:YES];
    
    
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] init];
    backItem.title=@"Back";
    [self.navigationItem setBackBarButtonItem:backItem];
    
    [backItem release];
    [profilePhotos release];
}

-(IBAction) btnEvents {
    ProfileEventsViewController *pevc = [[ProfileEventsViewController alloc] init];
    [self.navigationController pushViewController:pevc animated:YES];
    
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] init];
    backItem.title=@"Back";
    [self.navigationItem setBackBarButtonItem:backItem];
    
    [backItem release];
    [pevc release];
    
}


@end
