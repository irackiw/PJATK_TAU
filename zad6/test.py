import requests
import json
import logging
import inspect


def get_parent_function_name():
    return inspect.stack()[1][3]


def log_test(respone, function_name):
    if respone:
        logging.debug("WORKS: " + function_name)
    else:
        logging.error("ERROR: " + function_name)


class DogApiTest:

    def test_check_correct_response_status_code(self, res):
        assert res.status_code == 200
        log_test(True, get_parent_function_name())

    def test_wrong_url_response_status_code(self):
        wrong_url = "https://dog.ceo/api/breeds/list/alles"
        wrong_response = requests.request("GET", wrong_url)
        assert wrong_response.status_code == 404
        log_test(True, get_parent_function_name())

    def test_check_response_keys(self, json_data):
        expected = ['message', 'status']
        current = []

        for key, value in json_data.items():
            if key in expected:
                current.append(key)
        assert expected == current
        log_test(True, get_parent_function_name())

    def test_check_response_values(self, json_data, breed):
        status = ''
        d = []
        for key, value in json_data.items():
            if key == 'status':
                status = value
            if key == 'message':
                d = value
        for d in d:
            assert breed in d
        assert 'success' == status
        log_test(True, get_parent_function_name())

    def execute(self):
        logging.basicConfig(level=logging.DEBUG)
        logging.info('Start REST-API tests')
        hound_afghan_breed = 'hound'
        url = "https://dog.ceo/api/breed/hound/images"
        response = requests.request("GET", url)
        json_data = json.loads(response.text)

        try:
            self.test_check_correct_response_status_code(response)
            self.test_wrong_url_response_status_code()
            self.test_check_response_values(json_data, hound_afghan_breed)
            self.test_check_response_keys(json_data)
        except AssertionError as e:
            log_test(False, '')


test = DogApiTest()
test.execute()

