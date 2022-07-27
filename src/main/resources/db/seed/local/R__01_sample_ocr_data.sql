/* 샘플 OCR 데이터, 바운딩박스는 정확하지 않음 */

INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(1,1, 'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1288.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(2,2,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1289.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(3,3,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1290.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(4,4,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1291.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(5,5,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1292.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(6,6,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1293.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(7,7,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1294.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(8,8,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1295.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(9,9,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1296.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(10,10,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1297.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(11,11,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1298.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(12,12,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1299.JPG');
INSERT INTO ocr_data(id, raw_data_id, image_url) VALUES(13,13,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1300.JPG');


/* 임의의 바운딩 박스 */
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 10,10,10,20,20,10,20,20);

INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(2, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(2, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(3, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(3, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(4, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(4, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(5, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(5, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(6, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(6, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(7, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(7, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(8, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(8, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(9, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(9, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(10, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(10, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(11, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(11, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(12, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(12, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(13, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(13, 'RECTANGLE', 10,10,10,20,20,10,20,20);
