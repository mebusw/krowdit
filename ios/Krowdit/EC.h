//
//  EC.h
//  Krowdit
//
//  Created by jacky on 6/13/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//


#define UNSUCCESSFUL        -1     //operation not executed, generic error
#define SUCCESS             0      //operation executed successfully
#define TIMEOUT             1      //operation timeout 
#define EXISTED             3      //the target is existed in database
#define UNAUTHORIZED        5      //user is unauthorized to the operation
#define NOT_FOUND           7      //the target is not found in database
#define DAO_FAILED          9      //generic database error
#define ILLEGAL_ARGUMENT    11     //unsupported or illegal parameter
#define KROWD_CLOSED        100    //the krowd is closed, can't join it

