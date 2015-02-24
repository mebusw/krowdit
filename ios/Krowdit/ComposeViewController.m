//
//  ComposeViewController.m
//  Krowdit
//
//  Created by Jia Yong on 7/20/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//
#import "CameraViewController.h"
#import "ComposeViewController.h"
#import "KrowditAppDelegate.h"

@implementation ComposeViewController

@synthesize mytextView;

- (IBAction)cancel:(id)sender{
    [self dismissModalViewControllerAnimated:YES];
}
- (IBAction)send:(id)sender{
       
    [self.mytextView resignFirstResponder];
    str = mytextView.text;
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    dictForString = [NSDictionary dictionaryWithObjectsAndKeys:appDelegate.userBean.userName,@"mainTitleKey", str,@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"2",@"teamId",nil];
    
    [list addObject:dictForString];

    [list writeToFile:dataFilePath atomically:YES];
    
    [self dismissModalViewControllerAnimated:YES];


}

- (IBAction)photos:(id)sender{
    CameraViewController *cameraScreen = [[CameraViewController alloc] init];
    [self presentModalViewController:cameraScreen animated:YES];

    
    str = mytextView.text;
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    dictForString = [NSDictionary dictionaryWithObjectsAndKeys:appDelegate.userBean.userName,@"mainTitleKey", str,@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"2",@"teamId",nil];
    
    [list addObject:dictForString];
    [cameraScreen release];
}

- (void)focusInput {
	[mytextView becomeFirstResponder];
}
//设置焦点
- (void)viewDidAppear:(BOOL)animated {
	[self focusInput];
}
- (void)textChanged:(NSNotification *)notification{
	str = mytextView.text;
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
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
    [[NSNotificationCenter defaultCenter] addObserver:self 
											 selector:@selector(textChanged:) 
												 name:UITextViewTextDidChangeNotification 
											   object:mytextView];
    NSArray * documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString * documentDirectory = [documentPaths objectAtIndex:0];
    dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.post"];
    [dataFilePath retain];
    list = [NSMutableArray arrayWithContentsOfFile:dataFilePath];
    [list retain];

    self.view.backgroundColor = [UIColor clearColor];
    // Do any additional setup after loading the view from its nib.
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
- (void)dealloc {
    [dataFilePath release];
    [list release];
    [super dealloc];
}
@end
