//
//  UserBean.h
//  Krowdit
//
//  Created by jacky on 7/5/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 as a global variant, stores info of logged-in user
 */
@interface UserBean : NSObject {
    
}

@property (nonatomic) NSInteger userId;
@property (nonatomic, retain) NSString *userName;
@property (nonatomic, retain) NSString *password;
@property (nonatomic, retain) NSString *imageName;
@property (nonatomic) NSInteger userType;


@end
