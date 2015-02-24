//
//  JoinKrowdViewController.m
//  Krowdit
//
//  Created by jacky on 5/30/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "JoinKrowdViewController.h"
#import "KrowditAppDelegate.h"
#import "PostOfficeProxy.h"
#import "EC.h"
#import "FBConnect/FBConnect.h"
#import "WelcomeViewController.h"

/**Predefine the height of the cell */
#define WIDERCELL          200
#define NARRORCELL         100
#define TWOHOURSECONDS     7200
#define KROWDCREATE         1
#define USERCREATE          0

@implementation JoinKrowdViewController

@synthesize tmpCell;
@synthesize data;
@synthesize cellNib;
@synthesize flag;
@synthesize isChangeHeight;
@synthesize now_selectedCellIndexPath;
@synthesize pre_selectedCellIndexPath;
@synthesize imageDownloadsInProgress;

/** Define a variable reference to seleceted index*/
NSIndexPath *selectedCellIndexPath;

/** Define two variables to represent the latidude and longitude of the reference point*/
float lat;
float lng;

/** Define server time*/
NSString *serverTime;






- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)dealloc
{

    [tmpCell release];
    [data release];
    [cellNib release];
    [now_selectedCellIndexPath release];
    [pre_selectedCellIndexPath release];
    [imageDownloadsInProgress release];
    
    [super dealloc];
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}
#pragma mark - btnClick

- (void) btnClickLogout{
    DLog(@"btnClickLogout");
    NSDictionary *myDictionary = [NSDictionary dictionary];
    NSArray *documentPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentDirectory = [documentPaths objectAtIndex:0];
    NSString * dataFilePath = [documentDirectory stringByAppendingPathComponent:@"data.plist"];
    [myDictionary writeToFile:dataFilePath atomically:YES];
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    [appDelegate backtousernav];
    
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    imageDownloadsInProgress = [[NSMutableDictionary alloc]init];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    
    UIBarButtonItem *leftButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:nil];
    //    self.navigationItem.leftBarButtonItem = leftButton;
    self.navigationItem.leftBarButtonItem = leftButton;    
    [leftButton release];
    //UIBarButtonItem *rightButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:nil];
    
    self.navigationItem.rightBarButtonItem = [[[UIBarButtonItem alloc]
                                               initWithTitle:@"LogOut" style:UIBarButtonItemStylePlain target:self action:@selector(btnClickLogout)]autorelease];
    
    
    
    /** give the initial value to the present and previous index*/
    now_selectedCellIndexPath = [[NSIndexPath alloc] initWithIndex:100];
    pre_selectedCellIndexPath = [[NSIndexPath alloc] initWithIndex:100];
    
    /**give the initial bool value to false*/
    flag=false;
    isChangeHeight=false;
    
    /** Configure the table view*/
    self.tableView.backgroundColor=[UIColor darkGrayColor];
    self.tableView.separatorStyle=UITableViewCellSeparatorStyleSingleLine;
    
    /**Call the Proxy*/
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po listKrowdsAtLatitude:10 Longitude:10 offset:0 target:self tag:0];
    
    
    
    
    /**The UINib class provides better performance in situations where you 
     want to create multiple copies of a nib file's contents*/
    self.cellNib=[UINib nibWithNibName:@"SingleCell" bundle:nil];
    
    [super viewDidLoad];
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
    
    self.data = nil;
    self.tmpCell = nil;
    self.cellNib = nil;
}

- (void)viewWillAppear:(BOOL)animated
{
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
    // Return the number of rows in the section.
    
    //NSLog(@"abc=%d",[krowdList count]);
    return [krowdList count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    //DLog(@"cell");
    static NSString *CellIdentifier = @"CellData";
    
    SingleCell *cell=(SingleCell *)[tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        [self.cellNib instantiateWithOwner:self options:nil];
        cell = tmpCell;
        self.tmpCell = nil;
    }
    
    // Configure the cell...
    
    /**Congifure the data for the cell*/
    
    
    //DLog(@"zaas cell row= %d %d", indexPath.row, [krowdList count]);
    NSDictionary *dataItem=[krowdList objectAtIndex:indexPath.row];
    
    cell.name = [dataItem objectForKey:@"homeTeamName"];
    cell.awayName=[dataItem objectForKey:@"awayTeamName"];
    cell.placeName = [dataItem objectForKey:@"locationName"];
    cell.time = [dataItem objectForKey:@"startTime"];
    cell.homeSupportNumber = [[dataItem objectForKey:@"homeSupporterCount"] intValue];
    cell.awaySupportNumber = [[dataItem objectForKey:@"awaySupporterCount"] intValue];
    cell.size = cell.homeSupportNumber + cell.awaySupportNumber;
    cell.creatorName = [dataItem objectForKey:@"creatorName"];
    cell.createDate = [[dataItem objectForKey:@"createTime"] substringToIndex:11];

    cell.btnHome.tag = [indexPath row] * 2;
    cell.btnAway.tag = [indexPath row] * 2 + 1;

   
    
    
    if ([[dataItem objectForKey:@"creatorId"] intValue]==1) {
        cell.icon2=[UIImage imageNamed:@"K_logo_M.png"];
        cell.typeName = [dataItem objectForKey:@"krowdTypeName"];
    }
    else
        cell.typeNameLabel.hidden = YES;
    
    
    return cell;
}

NSInteger krowdSort(id k1, id k2, void *context)
{
    /** Get starttime from krowd1 and krowd2*/
    NSString *startTime1 = [k1 objectForKey:@"startTime"];
    NSString *startTime2 = [k2 objectForKey:@"startTime"];
    
    /** Change the time form of server,krowd1 and krowd2*/
    NSDateFormatter *df1=[[NSDateFormatter alloc] init];
    NSTimeZone *tzGmt=[NSTimeZone timeZoneWithName:@"GMT"];
    [df1 setTimeZone:tzGmt];
    [df1 setDateFormat:@"MM-dd-yyyy HH:mm:ss"];
    NSDate *date1 = [df1 dateFromString:startTime1];
    NSDate *date2 = [df1 dateFromString:startTime2];
    NSDate *date = [df1 dateFromString:serverTime];
    [df1 release];
    
    /**Calculate the time difference between the krowd and the servertime seperately,get the absolute value*/
    int timeDiff1=abs([date1 timeIntervalSinceDate:date]);
    int timeDiff2=abs([date2 timeIntervalSinceDate:date]);
    
    
    /**Get the creatorId from the two krowds*/
    int creID1=[[k1 objectForKey:@"creatorId"] intValue];
    int creID2=[[k2 objectForKey:@"creatorId"] intValue];
    
    
    /**Calculate the distance between the first krowd and the reference*/
    float lat1=[[k1 objectForKey:@"lat"] floatValue];
    float lng1=[[k1 objectForKey:@"lng"] floatValue];
    float c1=lat1-lat;
    float d1=lng1-lng;
    float e1=pow(c1, 2);
    float f1=pow(d1, 2);
    float distance1=e1+f1;
    
    /**Calculate the distance between the second krowd and the reference*/
    float lat2=[[k2 objectForKey:@"lat"] floatValue];
    float lng2=[[k2 objectForKey:@"lng"] floatValue];
    float c2=lat2-lat;
    float d2=lng2-lng;
    float e2=pow(c2, 2);
    float f2=pow(d2, 2);
    float distance2=e2+f2;
    
    
    /**The distances are the same*/
    if(distance1==distance2){
        
        /**One event happens with start times +/-2 hours from current time
         while another with start times>+/-2 but <+/-6 hours*/
        if(timeDiff1<TWOHOURSECONDS && TWOHOURSECONDS<timeDiff2){
            return NSOrderedAscending;
        }
        else if(timeDiff2<TWOHOURSECONDS && TWOHOURSECONDS<timeDiff1){
            return NSOrderedDescending;
        }
        
        /**Both events are with start times +/-2 hours or >+/-2 but <+/-6 hours from current time*/
        else{
            /**Judge whether the administrator or user create the event*/
            if(creID1==KROWDCREATE && creID2==KROWDCREATE){
                return NSOrderedSame;
            }
            else if(creID1==USERCREATE){
                return NSOrderedDescending;
            }
            else
                return NSOrderedAscending;
        }
    }
    else if(distance1<distance2)
        return NSOrderedAscending;
    else
        return NSOrderedDescending;
    
    
}


- (void) didListKrowds: (NSString *)string{
    NSDictionary *result=[string JSONValue];
    //    NSLog(@"%@\n%@",string,[result description]);
    //DLog(@"%@",[result description]);
    
    
    
    krowdList=[result objectForKey:@"krowds"];
    
    /** Get the value of latitude and longitude from krowd*/
    lat=[[result objectForKey:@"lat"] floatValue];
    lng=[[result objectForKey:@"lng"] floatValue];
    
    /** Get the server time from the krowds*/
    serverTime=[result objectForKey:@"serverTime"];
    
    
    
    
    
    
    
    homeTeamLogo=[[NSMutableArray alloc] init];
    awayTeamLogo=[[NSMutableArray alloc] init];
    
    //sort the krowd list by time_order, 'K'
    krowdList = [krowdList sortedArrayUsingFunction:krowdSort context:NULL];    
    [krowdList retain];
    
    for (NSDictionary *krowds in krowdList) {
        NSString *homeTeamTitle=[krowds objectForKey:@"homeTeamLogo"];
        NSString *awayTeamTitle=[krowds objectForKey:@"awayTeamLogo"];
        
        [homeTeamLogo addObject:homeTeamTitle];
        [awayTeamLogo addObject:awayTeamTitle];
    }
    [self.tableView reloadData];
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    
    for (int i=0; i<[krowdList count]; i++) {
        
        [po getImage:[homeTeamLogo objectAtIndex:i] target:self tag:2*i];
        [po getImage:[awayTeamLogo objectAtIndex:i] target:self tag:(2*i+1)];
    }
    [homeTeamLogo release];
    [awayTeamLogo release];
    
}



//the para dict is like this
//{
//image = "<UIImage: 0x4e37720>";
//json = "{\"error\":0, \"tag\":1}";
//}

- (void) didGetImage: (NSDictionary *) dict{
    
    NSDictionary *json = [[dict  objectForKey:@"json"] JSONValue];
    
    UIImage * img = [dict objectForKey:@"image"];
    //    NSLog(@"img is %@",img);
    
    int imgTag=[[json objectForKey:@"tag"] intValue];
    //NSLog(@"img is %d",imgTag);
    
    NSString *strForPicKeyHeader = [NSString stringWithString:@"pic"];
    NSString *strForPicKey = [strForPicKeyHeader stringByAppendingFormat:@"%d",imgTag];
    
    //NSLog(@"imgTag is  %d",imgTag);
    NSDictionary * temDict = [NSDictionary dictionaryWithObjectsAndKeys:img,strForPicKey,nil];
    
    [imageDownloadsInProgress addEntriesFromDictionary:temDict];
    //NSLog(@"imageDownloadsInProgress is %@",[imageDownloadsInProgress description]);
    
}


- (UIImage *) getImageByName : (NSString *)imaName{
    return [imageDownloadsInProgress objectForKey:imaName];
}
- (void)tableView:(UITableView *)tableView willDisplayCell:(SingleCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath{
    int i = [indexPath row];
    int imgHomeTag = i*2;
    int imgAwayTag = i*2+1;
    NSString *strForPicKeyHeader = [NSString stringWithString:@"pic"];
    NSString *strForPicKeyHome = [strForPicKeyHeader stringByAppendingFormat:@"%d",imgHomeTag];
    NSString *strForPicKeyAway = [strForPicKeyHeader stringByAppendingFormat:@"%d",imgAwayTag];
    
    UIImage * imgHome = [self getImageByName:strForPicKeyHome];
    UIImage * imgAway = [self getImageByName:strForPicKeyAway];
    //    NSLog(@"img is %@",imgHome);
    //    NSLog(@"img is %@",imgAway);
    
    cell.icon=imgHome;
    cell.icon1=imgAway;
    
    
    if(now_selectedCellIndexPath !=nil && [now_selectedCellIndexPath compare:indexPath] == NSOrderedSame &&isChangeHeight) {
        cell.iconView.hidden = NO;
        cell.iconView1.hidden = NO;
        cell.locationLabel.hidden = NO;
        cell.creatorLabel.hidden = NO;
        cell.createTimeLabel.hidden = NO;
        cell.homeSupportLabel.hidden = NO;
        cell.awaySupportLabel.hidden = NO;
        cell.creatorNameLabel.hidden = NO;
        cell.createDateLabel.hidden = NO;
        cell.showMessageLabel.hidden = NO;
        cell.backgroundColor=[UIColor grayColor];
    }
    else {
        cell.iconView.hidden=YES; 
        cell.iconView1.hidden=YES;
        cell.locationLabel.hidden=YES;
        cell.creatorLabel.hidden = YES;
        cell.createTimeLabel.hidden = YES;
        cell.homeSupportLabel.hidden = YES;
        cell.awaySupportLabel.hidden = YES;
        cell.creatorNameLabel.hidden = YES;
        cell.createDateLabel.hidden = YES;
        cell.showMessageLabel.hidden =YES;
    }  
    
    
    cell.selectionStyle=UITableViewCellSelectionStyleNone;
}

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
    
    [tableView deselectRowAtIndexPath:indexPath animated:NO];
    /**assign the index to the now selected*/
    now_selectedCellIndexPath = indexPath;
    
    /**logic judgement to change the bool value*/
    if (pre_selectedCellIndexPath == now_selectedCellIndexPath) {
        flag = !flag;
    }
    if (pre_selectedCellIndexPath == now_selectedCellIndexPath && flag) {
        isChangeHeight = true;
    }
    else{
        isChangeHeight = false;
    }
    if (pre_selectedCellIndexPath != now_selectedCellIndexPath) {
        flag = true;
        isChangeHeight = true;
    }
    
    
    
    /** These methods are used to call heightForRowAtIndexpath method to change the height of the cell*/
    [tableView scrollToNearestSelectedRowAtScrollPosition:UITableViewScrollPositionNone animated:YES];
    [tableView reloadRowsAtIndexPaths:[NSArray arrayWithObject:now_selectedCellIndexPath] withRowAnimation:UITableViewRowAnimationNone];
    if ([pre_selectedCellIndexPath compare:[NSIndexPath indexPathWithIndex:100]] == NSOrderedSame) {
        pre_selectedCellIndexPath=[NSIndexPath indexPathForRow:100 inSection:0];
    }
    if (pre_selectedCellIndexPath.row<[krowdList count]) {
        [tableView reloadRowsAtIndexPaths:[NSArray arrayWithObject:pre_selectedCellIndexPath] withRowAnimation:UITableViewRowAnimationFade];
    }
    
    /**assign the now selected index to the previous one*/
    pre_selectedCellIndexPath = now_selectedCellIndexPath;
}

#pragma mark - Search Display delegate

- (void)searchDisplayController:(UISearchDisplayController *)controller willShowSearchResultsTableView:(UITableView *)tableView {
    //NSLog(@"show result");
}

/**This method is used to change the height of the cell*/
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    //DLog(@"height %@ %d", now_selectedCellIndexPath, isChangeHeight);
    if(now_selectedCellIndexPath !=nil && [now_selectedCellIndexPath compare:indexPath] == NSOrderedSame && isChangeHeight)  {
        
        return WIDERCELL ;
    }    
    else
        return NARRORCELL;
    
}

- (IBAction) goImage:(id)sender{

    UIButton *btn = (UIButton*)sender;

    NSDictionary *krowd = [krowdList objectAtIndex:btn.tag / 2];
    
    KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
    appDelegate.krowdBean.locationName = [krowd objectForKey:@"locationName"];
    appDelegate.krowdBean.locationId = [[krowd objectForKey:@"locationId"] intValue];
    appDelegate.krowdBean.krowdId = [[krowd objectForKey:@"krowdId"] intValue];
    appDelegate.krowdBean.startTime = [krowd objectForKey:@"startTime"];
    appDelegate.krowdBean.homeTeamId = [[krowd objectForKey:@"homeTeamId"] intValue];
    appDelegate.krowdBean.awayTeamId = [[krowd objectForKey:@"awayTeamId"] intValue];
    appDelegate.krowdBean.homeTeamName = [krowd objectForKey:@"homeTeamName"];
    appDelegate.krowdBean.awayTeamName = [krowd objectForKey:@"awayTeamName"];
    appDelegate.krowdBean.homeTeamLogo = [krowd objectForKey:@"homeTeamLogo"];
    appDelegate.krowdBean.awayTeamLogo = [krowd objectForKey:@"awayTeamLogo"];
    appDelegate.krowdBean.creatorName = [krowd objectForKey:@"creatorName"];
    
    if (0 == btn.tag % 2) {
        appDelegate.krowdBean.supportingTeamId = appDelegate.krowdBean.homeTeamId;
    } else {
        appDelegate.krowdBean.supportingTeamId = appDelegate.krowdBean.awayTeamId;
    }
    
    PostOfficeProxy *po=[PostOfficeProxy sharedPostOfficeProxy];
    [po join:appDelegate.userBean.userId krowd:appDelegate.krowdBean.krowdId supportingTeam:appDelegate.krowdBean.supportingTeamId target:self tag:0];

    
}

- (void) didJoinKrowd: (NSString *)string {
    NSDictionary *json = [string JSONValue];
    int error = [[json objectForKey:@"error"] intValue];
    if (SUCCESS == error) {
        KrowditAppDelegate *appDelegate = [[UIApplication sharedApplication] delegate];
        appDelegate.krowdBean.postCount = [[json objectForKey:@"postCount"] intValue];
        appDelegate.krowdBean.postCount = [[json objectForKey:@"takenPicCount"] intValue];
        appDelegate.krowdBean.postCount = [[json objectForKey:@"memberCount"] intValue];
        
        WelcomeViewController *welcome = [[WelcomeViewController alloc] init];
        welcome.title = @"Welcome";
        [self.navigationController pushViewController:welcome animated:YES];
        [welcome release];
    }
}


@end
