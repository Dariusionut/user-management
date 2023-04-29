CREATE SEQUENCE IF NOT EXISTS seq_course;
CREATE TABLE IF NOT EXISTS course
(
    course_id              BIGINT   UNIQUE NOT NULL DEFAULT nextval('seq_course'),
    course_name            VARCHAR(65)  NOT NULL,
    fk_course_category     BIGINT   NOT NULL,

    CONSTRAINT course_pk PRIMARY KEY (course_id),
    CONSTRAINT course_fk_course_category FOREIGN KEY (fk_course_category) REFERENCES course_category (course_category_id)
    );

INSERT INTO course (course_name, fk_course_category)  VALUES ('Java Masterclass', '1');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Spring Boot 3 for Advanced', '1');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Intermediate Chinese', '3');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Public Speaking', '2');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Marketing Research', '2');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Git and Github', '1');
INSERT INTO course (course_name, fk_course_category)  VALUES ('HTML and CSS', '1');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Beginning French', '3');
INSERT INTO course (course_name, fk_course_category)  VALUES ('SEO Strategies', '2');
INSERT INTO course (course_name, fk_course_category)  VALUES ('JavaScript with Angular', '1');
INSERT INTO course (course_name, fk_course_category)  VALUES ('C++ Masterclass', '1');
INSERT INTO course (course_name, fk_course_category)  VALUES ('Advanced French', '3');

