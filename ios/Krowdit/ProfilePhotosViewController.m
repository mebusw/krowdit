//
//  ProfilePhotosViewController.m
//  Krowdit
//
//  Created by Jia Yong on 7/26/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#define PHOTO_TAG 1
#define PHOTO2_TAG 2
#define PHOTO3_TAG 3
#define PHOTO4_TAG 4

#import "ProfilePhotosViewController.h"


@implementation ProfilePhotosViewController

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

- (void)dealloc{
    [btnForPhotoClickAlertView release];
    [profilePhontoslist release];
    [super dealloc];
}
#pragma mark - View lifecycle

- (void)viewDidLoad
{
    
    [super viewDidLoad];

    self.title = @"My Photos";
    btnForPhotoClickAlertView = [[UIAlertView alloc] initWithTitle:@"Ur photos" message:@"I am J !\nHaha!" delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:@"Email me",@"Save to Album", nil];
    profilePhontoslist = [NSMutableArray array];
    [profilePhontoslist retain];
    for (int i = 1; i<17; i++) {
        [profilePhontoslist addObject:[NSString stringWithFormat:@"tb%d",i]];
    }
    self.view.backgroundColor = [UIColor darkGrayColor];
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
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
    [self.tableView scrollToNearestSelectedRowAtScrollPosition:UITableViewScrollPositionNone animated:YES];
    [super viewWillAppear:animated];
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

    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if ([profilePhontoslist count]%3) {
        return [profilePhontoslist count]/3 +1;
    }
    return [profilePhontoslist count]/3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *LeftCellIdentifier = @"Cell";
    
    UIImageView *photo;
    UIImageView *photo2;
    UIImageView *photo3;
    UIImageView *photo4;
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:LeftCellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:LeftCellIdentifier] autorelease];
        
        photo = [[[UIImageView alloc] initWithFrame:CGRectMake(16.0, 20.0, 60.0, 60.0)] autorelease];
        photo.tag = PHOTO_TAG;
        [cell.contentView addSubview:photo];
        UIButton * btnForPhoto1 = [UIButton buttonWithType:UIButtonTypeCustom];
        btnForPhoto1.frame = CGRectMake(16.0, 20.0, 60.0, 60.0);
        btnForPhoto1.backgroundColor = [UIColor clearColor];
        [btnForPhoto1 addTarget:self action:@selector(btnForPhotoClick) forControlEvents:UIControlEventTouchUpInside];
        [cell.contentView addSubview:btnForPhoto1];
        
        photo2 = [[[UIImageView alloc] initWithFrame:CGRectMake(92.0, 20.0, 60.0, 60.0)] autorelease];
        photo2.tag = PHOTO2_TAG;
        [cell.contentView addSubview:photo2];
        
        photo3 = [[[UIImageView alloc] initWithFrame:CGRectMake(168.0, 20.0, 60.0, 60.0)] autorelease];
        photo3.tag = PHOTO3_TAG;
        [cell.contentView addSubview:photo3];
        
        photo4 = [[[UIImageView alloc] initWithFrame:CGRectMake(244.0, 20.0, 60.0, 60.0)] autorelease];
        photo4.tag = PHOTO4_TAG;
        [cell.contentView addSubview:photo4];
        
    } else {
        photo = (UIImageView *)[cell.contentView viewWithTag:PHOTO_TAG];
        photo2 = (UIImageView *)[cell.contentView viewWithTag:PHOTO2_TAG];
        photo3 = (UIImageView *)[cell.contentView viewWithTag:PHOTO3_TAG];
        photo4 = (UIImageView *)[cell.contentView viewWithTag:PHOTO3_TAG];
    }
    
    
    int numForphotoId = indexPath.row * 3;
    NSString * photoName;
    NSString * imagePath;
    UIImage *theImage;
    
    if (numForphotoId < [profilePhontoslist count]) {
        photoName = [profilePhontoslist objectAtIndex:numForphotoId];
        imagePath = [[NSBundle mainBundle] pathForResource:photoName ofType:@"jpg"];
        theImage = [UIImage imageWithContentsOfFile:imagePath];
        photo.image = theImage;
    }
    
    numForphotoId = indexPath.row * 3 +1;
    if (numForphotoId < [profilePhontoslist count]) {
        photoName = [profilePhontoslist objectAtIndex:numForphotoId];
        imagePath = [[NSBundle mainBundle] pathForResource:photoName ofType:@"jpg"];
        theImage = [UIImage imageWithContentsOfFile:imagePath];
        photo2.image = theImage;
    }
    
    
    numForphotoId = indexPath.row * 3 +2;
    if (numForphotoId < [profilePhontoslist count]) {
        photoName = [profilePhontoslist objectAtIndex:numForphotoId];
        imagePath = [[NSBundle mainBundle] pathForResource:photoName ofType:@"jpg"];
        theImage = [UIImage imageWithContentsOfFile:imagePath];
        photo3.image = theImage;
    }
    
    numForphotoId = indexPath.row * 3 +3;
    if (numForphotoId < [profilePhontoslist count]) {
        photoName = [profilePhontoslist objectAtIndex:numForphotoId];
        imagePath = [[NSBundle mainBundle] pathForResource:photoName ofType:@"jpg"];
        theImage = [UIImage imageWithContentsOfFile:imagePath];
        photo4.image = theImage;
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
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 80;
    
}
-(void)btnForPhotoClick{
    
    [btnForPhotoClickAlertView show];
}
@end
