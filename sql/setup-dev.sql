use genti;

set @adminId := 1;
set @userId := 2;
set @emptyUserId := 3; # 아무 사진 생성 요청하지 않은 유저의 UserId
set @creatorUserId := 4; # 공급자 UserId
set @emptyCreatorId := 5; # 사진 생성 요청 매칭되지 않은 공급자의 UserId
set @oauthFirstJoinUserId := 6; # 첫 회원가입 유저 - FE에서는 크게 신경쓸 일 없을 것 같긴 합니다.
set @deactivatedUserId := 7; # 비활성화된 유저의 UserId
set @deactivatedCreatorId := 8; # 비활성화된 공급자의 CreatorId

set @creatorId := 1; # 공급자의 CreatorId
set @adminCreatorId := 3; # 어드민의 공급자 id

insert ignore into user (id, created_at, modified_at, deleted_at, email, email_verified, introduction,
                         last_login_social_platform, login_id, nickname, password, user_role, roles, user_status,
                         username, sex)

VALUES (1, localtime, localtime, null, 'admin@gmail.com', true, null, 'GOOGLE', null, '어드민테스트1_닉네임', null, 'ROLE_ADMIN',
        'ROLE_ADMIN,ROLE_MANAGER,ROLE_CREATOR,ROLE_USER', 'ACTIVATED', '어드민이름', 'NONE'),
       (2, localtime, localtime, null, 'user@gmail.com', true, '유저_소개1', 'GOOGLE', null, '유저테스`트1_닉네임', null,
        'ROLE_USER', 'ROLE_USER', 'ACTIVATED', '유저이름1', 'M'),
       (3, localtime, localtime, null, 'emptyUser@gmail.com', true, '유저_소개2', 'GOOGLE', null, '유저테스트1_닉네임', null,
        'ROLE_USER', 'ROLE_USER', 'ACTIVATED', '유저이름2', 'W'),
       (4, localtime, localtime, null, 'creator@gmail.com', true, '공급자_소개', 'GOOGLE', null, '유저테스트2_닉네임', null,
        'ROLE_CREATOR', 'ROLE_CREATOR', 'ACTIVATED', '공급자이름1', 'M'),
       (5, localtime, localtime, null, 'emptyCreator@gmail.com', true, '공급자_소개2', 'GOOGLE', null, '공급자테스트2_닉네임', null,
        'ROLE_CREATOR', 'ROLE_CREATOR', 'ACTIVATED', '공급자이름2', 'M'),
       (6, localtime, localtime, null, 'oauthFirstJoin@gmail.com', true, '최초가입자_소개', 'GOOGLE', null, '최초가입자_닉네임', null,
        'ROLE_OAUTH_FIRST_JOIN', 'ROLE_OAUTH_FIRST_JOIN,ROLE_USER', 'ACTIVATED', '최초가입자이름', 'M'),
       (7, localtime, localtime, null, 'deactivatedUser@gmail.com', true, '최초가입자_소개', 'GOOGLE', null, '비활성화된유저_닉네임',
        null, 'ROLE_USER', 'ROLE_USER', 'DEACTIVATED', '비활성화된유저이름', 'M'),
       (8, localtime, localtime, null, 'deactivatedCreator@gmail.com', true, '최초가입자_소개', 'GOOGLE', null, '비활성화된공급자_닉네임',
        null, 'ROLE_CREATOR', 'ROLE_CREATOR', 'DEACTIVATED', '비활성화된공급자이름', 'M');

insert ignore into creator (id, created_at, modified_at, account_holder, account_number, bank_type, workable, user_id)
values (1, localtime, localtime, '서병렬', '123-4142-2523331-123', '국민은행', true, 4),
       (2, localtime, localtime, null, null, null, false, 5),
       (3, localtime, localtime, null, null, null, true, 1);

insert ignore into picture_pose (id, created_at, modified_at, `key`, uploaded_by)
values (1, LOCALTIME, LOCALTIME, 'pose_picture_url1', 2),
       (2, LOCALTIME, LOCALTIME, 'pose_picture_url2', 2),
       (3, LOCALTIME, LOCALTIME, 'pose_picture_url3', 2),
       (4, LOCALTIME, LOCALTIME, 'pose_picture_url4', 2);

insert ignore into picture_user_face (id, created_at, modified_at, `key`, uploaded_by)
values (1, LOCALTIME, LOCALTIME, 'user_face_picture_url1', 2),
       (2, LOCALTIME, LOCALTIME, 'user_face_picture_url2', 2),
       (3, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2),
       (4, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2),
       (5, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2),
       (6, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2),
       (7, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2),
       (8, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2),
       (9, LOCALTIME, LOCALTIME, 'user_face_picture_url3', 2);


insert ignore into picture_generate_request (id, creator_id, picture_pose_id, requester_id, prompt, prompt_advanced,
                                             camera_angle,
                                             request_status, shot_coverage, created_at, modified_at)
VALUES (1, null, 4, 2, '생성된요청', 'prompt_advanced_test_1', '위에서 촬영', 'CREATED', '얼굴만 클로즈업',
        '2024-05-08 07:00:00', '2024-05-08 07:00:00'),
       (2, null, 3, 2, '취소된요청', 'prompt_advanced_test_2', '같은 높이에서 촬영', 'CANCELED', '허리 위로 촬영',
        '2024-05-08 13:00:00', '2024-05-08 13:00:00'),
       (3, 1, 2, 2, '특정 공급자와 매칭중인요청', 'prompt_advanced_test_3', '아래에서 촬영', 'ASSIGNING', '무릎 위로 촬영',
        '2024-05-29 07:00:00', '2024-05-29 07:00:00'),
       (4, 1, 2, 2, '매칭 후 진행중인 요청', 'prompt_advanced_test_4', '아래에서 촬영', 'IN_PROGRESS', '무릎 위로 촬영',
        '2024-05-29 08:00:00', '2024-05-29 08:00:00'),
       (5, 1, 2, 2, '매칭 후 진행중인 요청', 'prompt_advanced_test_5', '아래에서 촬영', 'IN_PROGRESS', '무릎 위로 촬영',
        '2024-05-29 09:00:00', '2024-05-29 09:00:00'),
       (6, 1, 2, 2, '신고된 요청', 'prompt_advanced_test_6', '아래에서 촬영', 'REPORTED', '무릎 위로 촬영', '2024-05-29 10:00:00',
        '2024-05-29 10:00:00'),
       (7, 1, 1, 2, '완료된 요청', 'prompt_advanced_test_7', '위에서 촬영', 'COMPLETED', '전신 촬영', '2024-05-29 10:00:00',
        '2024-05-29 11:00:00'),
       (8, 1, 1, 2, '신고된 요청', 'prompt_advanced_test_8', '위에서 촬영', 'REPORTED', '전신 촬영', '2024-05-29 12:00:00',
        '2024-05-29 12:00:00'),
       (9, 3, 3, 2, 'prompt_test_9', 'prompt_advanced_test_9', '위에서 촬영', 'MATCH_TO_ADMIN', '전신 촬영',
        '2024-05-29 13:00:00', '2024-05-29 13:00:00');

insert ignore into picture_generate_request_picture_user_face (picture_generate_request_id, user_face_picture_list_id)

values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3),
       (5, 4),
       (5, 5),
       (5, 6),
       (6, 4),
       (6, 5),
       (6, 6),
       (7, 4),
       (7, 5),
       (7, 6),
       (8, 7),
       (8, 8),
       (8, 9),
       (9, 7),
       (9, 8),
       (9, 9);

insert ignore into picture_generate_response (id, created_at, modified_at, memo, status, creator_id, request_id,
                                              submitted_by_admin_at, submitted_by_creator_at)
values (1, '2024-05-29 07:00:02', null, null, 'BEFORE_WORK', 1, 3, null, null),
       (2, '2024-05-29 08:00:02', '2024-05-29 10:30:00', 'memo', 'SUBMITTED_FIRST', 1, 4, '2024-05-29 10:30:00', null),
       (3, '2024-05-29 09:00:02', '2024-05-29 10:20:00', 'memo', 'SUBMITTED_FINAL', 1, 5, '2024-05-29 10:20:00',
        '2024-05-29 11:20:00'),
       (4, '2024-05-29 10:00:02', '2024-05-29 11:00:02', 'memo', 'REPORTED', 1, 6, '2024-05-29 11:00:02',
        '2024-05-29 12:20:00'),
       (5, '2024-05-29 11:00:02', '2024-05-29 13:00:02', 'memo', 'COMPLETED', 1, 7, '2024-05-29 13:00:02',
        '2024-05-29 18:00:02'),
       (6, '2024-05-29 12:00:02', '2024-05-29 14:00:02', 'memo', 'REPORTED', 1, 8, '2024-05-29 14:00:02',
        '2024-05-29 15:20:00');

insert ignore into report (id, created_at, modified_at, content, report_status, picture_generate_response_id)
values (1, '2024-05-29 10:30:00', null, '변태자식이 본인 발가락 사진을 보낸 것 같습니다.', 'NOT_RESOLVED', 4),
       (2, '2024-05-29 12:00:00', null, '모르는 사람 얼굴이에요', 'RESOLVED', 6);

insert ignore into picture_created_by_creator (id, created_at, modified_at, `key`, picture_generate_response_id,
                                               uploaded_by)
values (1, localtime, localtime, '얼굴 완성 전 url 1', 2, 4),
       (2, localtime, localtime, '얼굴 완성 전 url 2', 3, 4),
       (3, localtime, localtime, '얼굴 완성 전 url 3', 4, 4);

insert ignore into picture_completed (id, created_at, modified_at, `key`, uploaded_by, picture_generate_response_id,
                                      requester_id)
values (1, localtime, localtime, '얼굴 완성 사진 url 1', 1, 3, 2),
       (2, localtime, localtime, '얼굴 완성 사진 url 2', 1, 4, 2),
       (3, localtime, localtime, '얼굴 완성 사진 url 3', 1, 5, 2),
       (4, localtime, localtime, '얼굴 완성 사진 url 4', 1, 6, 2);

insert ignore into response_example (id, created_at, modified_at, `key`, example_prompt, prompt_only,
                                     uploaded_by)
values (1, localtime, localtime, '/EXAMPLE/벚꽃벤치여자.png', '벚꽃 벤치 여자', false, 1),
       (2, localtime, localtime, '/EXAMPLE/한강잠수부.png', '한강잠수부', false, 1),
       (3, localtime, localtime, null, '프롬프트만있음 사진은 없고', true, 1);

insert ignore into settlement (id, created_at, modified_at, elapsed_minutes, reward, picture_generate_response_id,
                               settlement_status)
values (1, localtime, localtime, 80, 2000, 2, 'WITHDRAWN'),
       (2, localtime, localtime, 50, 2500, 3, 'CREATED'),
       (3, localtime, localtime, 10, 2500, 4, 'CANCELLED'),
       (4, localtime, localtime, 230, 1000, 5, 'CREATED');

insert ignore into deposit (id, created_at, modified_at, deposit_amount, user_id)
values (1, localtime, localtime, 0, 1),
       (2, localtime, localtime, 1000, 2),
       (3, localtime, localtime, 0, 3),
       (4, localtime, localtime, 0, 4),
       (5, localtime, localtime, 0, 5),
       (6, localtime, localtime, 0, 6),
       (7, localtime, localtime, 0, 7),
       (8, localtime, localtime, 0, 8);

# insert ignore into post (id, user_id, main_picture_id, content, likes, post_status, created_at)
# values (1, 3, 1, 'post content 테스트', 1001, 'POSTED', '20010101')
#      , (2, 3, 3, 'post content 테스트2', 1002, 'POSTED', '20010102')
#      , (3, 4, 4, 'deleted 테스트1', 1003, 'DELETED', '20010103')
#      , (4, 3, 7, 'post content 테스트3', 1004, 'POSTED', '20010104')
#      , (5, 3, 9, 'post content 테스트4', 5, 'POSTED', '20010105')
#      , (6, 4, 12, 'post content 테스트5', 1006, 'POSTED', '20010106')
#      , (7, 3, 13, 'post content 테스트6', 1007, 'POSTED', '20010107')
#      , (8, 3, 15, 'post content 테스트7', 1008, 'POSTED', '20010108')
#      , (9, 3, 16, 'post content 테스트8', 1009, 'POSTED', '20010109')
#      , (10, 3, 17, 'post content 테스트9', 1010, 'POSTED', '20010110')
#      , (11, 3, 21, 'post content 테스트10', 1011, 'POSTED', '2001011')
#      , (12, 3, 22, 'post content 테스트11', 1012, 'POSTED', '20010112')
#      , (13, 3, 27, 'post content 테스트12', 1013, 'POSTED', '20010113');


# insert ignore into post_picture (id, picture_id, post_id, created_at)
# values (1, 1, 1, '20020101'),
#        (2, 2, 1, '20020102'),
#        (3, 3, 2, '20020103'),
#        (4, 4, 3, '20020104'),
#        (5, 7, 4, '20020104'),
#        (6, 8, 5, '20020101'),
#        (7, 9, 5, '20020102'),
#        (8, 10, 6, '20020103'),
#        (9, 11, 6, '20020104'),
#        (10, 12, 6, '20020104'),
#        (11, 13, 7, '20020101'),
#        (12, 14, 7, '20020102'),
#        (13, 15, 8, '20020103'),
#        (14, 16, 9, '20020104'),
#        (15, 17, 10, '20020104'),
#        (16, 18, 10, '20020101'),
#        (17, 19, 10, '20020102'),
#        (18, 20, 10, '20020103'),
#        (19, 21, 11, '20020104'),
#        (20, 22, 12, '20020104'),
#        (21, 23, 13, '20020101'),
#        (22, 24, 13, '20020102'),
#        (23, 25, 13, '20020103'),
#        (24, 26, 13, '20020104'),
#        (25, 27, 13, '20020104');

#
# insert ignore into post_like (id, post_id, user_id)
# values (1, 1, 3),
#        (2, 2, 3),
#        (3, 2, 3),
#        (4, 3, 4),
#        (5, 4, 4);

#
# insert ignore into picture_profile (id, created_at, modified_at, url)
# values (1, localtime, localtime, 'profile_url1'),
#        (2, localtime, localtime, 'profile_url2');
