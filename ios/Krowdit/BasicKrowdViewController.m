//
//  BasicKrowdViewController.m
//  Krowdit
//
//  Created by Jia Yong on 7/15/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#define MAINLABEL_TAG 1
#define SECONDLABEL_TAG 2
#define PHOTO_TAG 3
#import "BasicKrowdViewController.h"
#import "ComposeViewController.h"
#import "KrowditAppDelegate.h"

@implementation BasicKrowdViewController

//@synthesize postData;
@synthesize list;

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
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
    self.title = @"Krowd";
    self.navigationItem.hidesBackButton = TRUE;
    self.view.backgroundColor = [UIColor orangeColor];
    
    NSArray * documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString * documentDirectory = [documentPaths objectAtIndex:0];
    dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.post"];
    [dataFilePath retain];
    
    NSDictionary * dict1 = [NSDictionary dictionaryWithObjectsAndKeys:@"MJ",@"mainTitleKey", @"GO!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"2",@"teamId",nil];
    NSDictionary * dict2 = [NSDictionary dictionaryWithObjectsAndKeys:@"Pete",@"mainTitleKey", @"Yeah, u r only down 9 points now!",@"secondaryTitleKey",@"Pete_Logo",@"imageKey",@"1",@"teamId",nil];
    NSDictionary * dict3 = [NSDictionary dictionaryWithObjectsAndKeys:@"Dwight",@"mainTitleKey", @"MJ,disregard Pete.Game is on and he is forgetful!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"2",@"teamId",nil];
    NSDictionary * dict4 = [NSDictionary dictionaryWithObjectsAndKeys:@"Pam",@"mainTitleKey", @"Here's to Pete at the CB's!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"1",@"teamId",nil];
    NSDictionary * dict5 = [NSDictionary dictionaryWithObjectsAndKeys:@"Cindy",@"mainTitleKey", @"GO!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"1",@"teamId",nil];
    NSDictionary * dict6 = [NSDictionary dictionaryWithObjectsAndKeys:@"Ryan",@"mainTitleKey", @"GO!",@"secondaryTitleKey",@"MJ_logo",@"imageKey",@"1",@"teamId",nil];
    list = [NSMutableArray arrayWithObjects:dict1,dict2,dict3,dict4,dict5,dict6,nil];
    [list writeToFile:dataFilePath atomically:YES];
    

    [list retain];

    UIBarButtonItem* saveItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd
                                                                              target:self
                                                                              action:@selector(clickAddPostBtn)];
    saveItem.style = UIBarButtonItemStylePlain;
	self.navigationItem.rightBarButtonItem = saveItem;
	[saveItem release];
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}
-(void) clickAddPostBtn{
    ComposeViewController * composeViewController = [[ComposeViewController alloc] init];
    [self presentModalViewController:composeViewController animated:YES];
    //    BasicKrowdViewController *welcome = [[BasicKrowdViewController alloc] init];
    //    [self.navigationController pushViewController:welcome animated:NO];
    [composeViewController release];
    
}
- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    list = [[NSMutableArray alloc]initWithContentsOfFile:dataFilePath];
    //DLog(@"bbb %d", [list count]);
    [self.tableView reloadData];

}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
//#warning Potentially incomplete method implementation.
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
//#warning Incomplete method implementation.
    // Return the number of rows in the section.
    return [list count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *LeftCellIdentifier = @"LeftCellIdentifier";
    
    UILabel *mainLabel, *secondLabel;
    UIImageView *photo;
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:LeftCellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:LeftCellIdentifier] autorelease];
        
        mainLabel = [[[UILabel alloc] initWithFrame:CGRectMake(10.0, 5.0, 220.0, 15.0)] autorelease];
        mainLabel.backgroundColor = [UIColor clearColor];
        mainLabel.tag = MAINLABEL_TAG;
        mainLabel.font = [UIFont systemFontOfSize:14.0];
        mainLabel.textColor = [UIColor blackColor];
        mainLabel.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleHeight;
        [cell.contentView addSubview:mainLabel];
        
        secondLabel = [[[UILabel alloc] initWithFrame:CGRectMake(10.0, 15.0, 220.0, 25.0)] autorelease];
        secondLabel.backgroundColor = [UIColor clearColor];
        secondLabel.tag = SECONDLABEL_TAG;
        secondLabel.font = [UIFont systemFontOfSize:12.0];
        secondLabel.textColor = [UIColor blackColor];
        secondLabel.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleHeight;
        [cell.contentView addSubview:secondLabel];
        
        photo = [[[UIImageView alloc] initWithFrame:CGRectMake(255.0, 0.0, 45.0, 45.0)] autorelease];
        photo.tag = PHOTO_TAG;
        photo.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleHeight;
        [cell.contentView addSubview:photo];
    } else {
        mainLabel = (UILabel *)[cell.contentView viewWithTag:MAINLABEL_TAG];
        secondLabel = (UILabel *)[cell.contentView viewWithTag:SECONDLABEL_TAG];
        photo = (UIImageView *)[cell.contentView viewWithTag:PHOTO_TAG];
    }
    //DLog(@"%d",[list count]);
    //DLog(@"indexPath.row is %d",indexPath.row);
    NSDictionary *aDict = [self.list objectAtIndex:indexPath.row];
    if (2 == [[aDict objectForKey:@"teamId"] intValue]) {
        cell.textLabel.text = [aDict objectForKey:@"mainTitleKey"];
        cell.detailTextLabel.text = [aDict objectForKey:@"secondaryTitleKey"];
        cell.imageView.image = [UIImage imageNamed:@"MJ_logo.png"];
        mainLabel.text = nil;
        secondLabel.text = nil;
        photo.image = nil;
    }else if (1 == [[aDict objectForKey:@"teamId"] intValue]){
        mainLabel.text = [aDict objectForKey:@"mainTitleKey"];
        secondLabel.textColor = [UIColor blackColor];
        secondLabel.text = [aDict objectForKey:@"secondaryTitleKey"];
        NSString *imagePath = [[NSBundle mainBundle] pathForResource:[aDict objectForKey:@"imageKey"] ofType:@"png"];
        UIImage *theImage = [UIImage imageWithContentsOfFile:imagePath];
        photo.image = theImage;
        cell.textLabel.text =nil;
        cell.detailTextLabel.text = nil;
        cell.imageView.image = nil;
    }
    

    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    return cell;
}

/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    cell.backgroundColor = [UIColor orangeColor];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Navigation logic may go here. Create and push another view controller.
    /*
     <#DetailViewController#> *detailViewController = [[<#DetailViewController#> alloc] initWithNibName:@"<#Nib name#>" bundle:nil];
     // ...
     // Pass the selected object to the new view controller.
     [self.navigationController pushViewController:detailViewController animated:YES];
     [detailViewController release];
     */
}
-(void)dealloc{
    [list release];
    [dataFilePath release];
    [super dealloc];
}
@end
