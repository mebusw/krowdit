//
//  Welcome.m
//  Krowdit
//
//  Created by jacky on 7/13/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "WelcomeViewController.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "ComposeViewController.h"
#import "BasicKrowdViewController.h"

#import "CameraViewController.h"
@implementation WelcomeViewController
@synthesize welcomeUsername;
@synthesize welcomeLocation;
@synthesize imageView;
@synthesize composeViewController;

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
    
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    self.navigationItem.hidesBackButton = TRUE;
    
    self.welcomeUsername.text = [NSString stringWithFormat:@"Hey, %@", appDelegate.userBean.userName];
    self.welcomeLocation.text = [NSString stringWithFormat:@"You've now joined the Krowd at %@!", appDelegate.krowdBean.locationName];
    
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po getImage:appDelegate.userBean.imageName target:self tag:0];
    
}

- (void) viewWillAppear:(BOOL)animated {
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];

    if(appDelegate.krowdBean.krowdId < 0) {
        [self.navigationController popViewControllerAnimated:NO];
    }
    
}

- (void) didGetImage: (NSDictionary *) dict {
    NSDictionary *json = [[dict  objectForKey:@"json"] JSONValue];
    if (SUCCESS == [[json objectForKey:@"error"] intValue]) {
        UIImage * img = [dict objectForKey:@"image"];
        self.imageView.image = img;
    }
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
-(IBAction) clickBtnPostScreen {
    composeViewController = [[ComposeViewController alloc] init];
    [self presentModalViewController:composeViewController animated:YES];
BasicKrowdViewController * postViewController = [[BasicKrowdViewController alloc] init];
[self.navigationController pushViewController:postViewController animated:NO];
    [composeViewController release];
    [postViewController release];

    
}

-(IBAction) clickBtnKrowdScreen {
    BasicKrowdViewController *welcome = [[BasicKrowdViewController alloc] init];
    [self.navigationController pushViewController:welcome animated:YES];
    
    /**set the back button*/
    UIBarButtonItem *backItem=[[UIBarButtonItem alloc] init];
    backItem.title=@"Back";
    [self.navigationItem setBackBarButtonItem:backItem];
    
    [backItem release];
    [welcome release];
    
}

-(IBAction) clickBtnCameraScreen {
    CameraViewController *cameraScreen = [[CameraViewController alloc] init];
    cameraScreen.delegate = self;
    [self presentModalViewController: cameraScreen animated:YES];

    
}

-(void) didPickingImage: (UIImage*) image {
    //TODO
}
@end
