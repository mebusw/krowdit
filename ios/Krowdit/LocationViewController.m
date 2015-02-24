//
//  LocationViewController.m
//  Krowdit
//
//  Created by fujing on 7/18/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "LocationViewController.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "JoinKrowdViewController.h"
#import "WelcomeViewController.h"

@implementation LocationViewController
@synthesize homeTeamName;
@synthesize homeTeamImage;
@synthesize homeSupportCount;
@synthesize startTime;
@synthesize fieldName;
@synthesize creatorName;
@synthesize memberCount;
@synthesize postCount;
@synthesize takenPicCount;
@synthesize awayTeamName;
@synthesize awayTeamImage;
@synthesize awaySupportCount;

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
    [homeTeamName release];
    [homeTeamImage release];
    [homeSupportCount release];
    [fieldName release];
    [startTime release];
    [creatorName release];
    [memberCount release];
    [postCount release];
    [takenPicCount release];
    [awayTeamName release];
    [awayTeamImage release];
    [awaySupportCount release];
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
    self.navigationItem.rightBarButtonItem = [[[UIBarButtonItem alloc] initWithTitle:@"Leave" style:UIBarButtonItemStylePlain target:self action:@selector(goBackKrowd)] autorelease];
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po join:1 krowd:2 supportingTeam:1 target:self tag:0];
    
}

- (void)goBackKrowd{
    self.tabBarController.selectedIndex = 0;
    //[self.tabBarController.viewControllers objectAtIndex:0]////
    
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    [appDelegate.kroNav popToRootViewControllerAnimated:YES];
    appDelegate.krowdBean.krowdId = -1;
    appDelegate.krowdBean.locationId = -1;
    appDelegate.krowdBean.locationName = @"";
    
}

- (void) viewWillAppear:(BOOL)animated{
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    
    self.fieldName.text = [NSString stringWithFormat:@"%@",appDelegate.krowdBean.locationName];
    self.homeSupportCount.text = [NSString stringWithFormat:@"%d Supporters",appDelegate.krowdBean.homeTeamId];
    self.awaySupportCount.text = [NSString stringWithFormat:@"%d Supporters",appDelegate.krowdBean.awayTeamId];
    self.homeTeamName.text = [NSString stringWithFormat:@"%@", appDelegate.krowdBean.homeTeamName];
    self.awayTeamName.text = [NSString stringWithFormat:@"%@", appDelegate.krowdBean.awayTeamName];
    self.startTime.text = [NSString stringWithFormat:@"This game started at %@",appDelegate.krowdBean.startTime];
    self.creatorName.text = [NSString stringWithFormat:@"%@ create this event",appDelegate.krowdBean.creatorName];
    self.memberCount.text = [NSString stringWithFormat:@"Peak Krowd size is %d members",appDelegate.krowdBean.memberCount];
    self.postCount.text = [NSString stringWithFormat:@"%d Posts have been created",appDelegate.krowdBean.postCount];
    self.takenPicCount.text = [NSString stringWithFormat:@"%d Pictures have been taken",appDelegate.krowdBean.takenPicCount];
    self.homeTeamImage.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@",appDelegate.krowdBean.homeTeamLogo]];
    self.awayTeamImage.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@",appDelegate.krowdBean.awayTeamLogo]];
}

- (void) didJoinKrowd: (NSString *)string{
    NSDictionary *result = [string JSONValue];
     DLog(@"location is %@",[result description]);
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    appDelegate.krowdBean.memberCount = [[result objectForKey:@"memberCount"] intValue];
    appDelegate.krowdBean.postCount = [[result objectForKey:@"postCount"] intValue];
    appDelegate.krowdBean.takenPicCount = [[result objectForKey:@"takenPicCount"] intValue];
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
-(IBAction) goMap:(id)sender{
    MapViewController *mapView = [[MapViewController alloc] init];
    [self.navigationController pushViewController:mapView animated:YES];
    
    UIBarButtonItem *backItem=[[UIBarButtonItem alloc] init];
    backItem.title=@"Back";
    [self.navigationItem setBackBarButtonItem:backItem];
    
    [backItem release];
    [mapView release];
}


@end
