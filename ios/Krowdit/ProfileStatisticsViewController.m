//
//  ProfileStatisticsViewController.m
//  Krowdit
//
//  Created by fujing on 7/22/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "ProfileStatisticsViewController.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "EC.h"

@implementation ProfileStatisticsViewController
@synthesize totalKrowdsJoined;
@synthesize largeKrowdJoined;
@synthesize textPostCount;
@synthesize picPostCount;
@synthesize largestKrowdMemberCount;
@synthesize favoriteKrowdType;
@synthesize favoriteLocation;

NSString *location;
NSString *fType;
NSInteger totalJoined;
NSInteger largeJoined;
NSInteger textPosts;
NSInteger picPostes;
NSInteger largeCount;

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
    [totalKrowdsJoined release];
    [largeKrowdJoined release];
    [textPostCount release];
    [picPostCount release];
    [largestKrowdMemberCount release];
    [favoriteKrowdType release];
    [favoriteLocation release];
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
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    self.title = [NSString stringWithFormat:@"%@'s Statistics",appDelegate.userBean.userName];

    
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po getUserStatistics:2 target:self tag:0];
    

    self.favoriteLocation.text = location;
    self.favoriteKrowdType.text = fType;
    self.totalKrowdsJoined.text = [NSString stringWithFormat:@"%d",totalJoined];
    self.largeKrowdJoined.text = [NSString stringWithFormat:@"%d",largeJoined];
    self.textPostCount.text = [NSString stringWithFormat:@"%d", textPosts];
    self.picPostCount.text = [NSString stringWithFormat:@"%d", picPostes];
    self.largestKrowdMemberCount.text = [NSString stringWithFormat:@"%d Members",largeCount];
   
}

- (void) didGetUserStatistics: (NSString *)string{
    NSDictionary *stasticList = [string JSONValue];
    location =[stasticList objectForKey:@"favoriteLocation"];
    fType = [stasticList objectForKey:@"favoriteKrowdType"];
    totalJoined = [[stasticList objectForKey:@"totalKrowdsJoined"] intValue];
    largeJoined = [[stasticList objectForKey:@"LargeKrowdJoined"] intValue];
    textPosts = [[stasticList objectForKey:@"textPostCount"] intValue];
    picPostes = [[stasticList objectForKey:@"picPostCount"] intValue];
    largeCount = [[stasticList objectForKey:@"largestKrowdMemberCount"] intValue];
    
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

@end
