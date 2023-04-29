CREATE TABLE IF NOT EXISTS course_category
(
    course_category_id              BIGINT  UNIQUE    NOT NULL,
    course_category_name            VARCHAR(65) UNIQUE NOT NULL,

    CONSTRAINT course_category_pk PRIMARY KEY (course_category_id)
    );

INSERT INTO course_category(course_category_id, course_category_name)
VALUES (1, 'PROGRAMMING')
    ON CONFLICT (course_category_id) DO NOTHING;

INSERT INTO course_category(course_category_id, course_category_name)
VALUES (2, 'BUSINESS')
    ON CONFLICT (course_category_id) DO NOTHING;

INSERT INTO course_category(course_category_id, course_category_name)
VALUES (3, 'FOREIGN LANGUAGES')
    ON CONFLICT (course_category_id) DO NOTHING;