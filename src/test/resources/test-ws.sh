echo Auth
wget -O rest  'http://localhost:52757/douckonline1/rest/login/get-auth-token?username=MomMilly&password=student-password'
wget -O rest  'http://localhost:52757/douckonline1/rest/login/check-auth-token?username=MomMilly&auth-token=%2B80PwdEniX1XRrydv/J87Q=='

wget -O rest  'http://localhost:52757/douckonline1/rest/login/get-auth-token?username=JamieHydrogen&password=H2-password'
wget -O rest  'http://localhost:52757/douckonline1/rest/login/check-auth-token?username=JamieHydron=s7uRj0qs3%2Bd5DmjNhQO3jQ=='


echo Student
wget -O rest  'http://localhost:52757/douckonline1/rest/student/profile?username=MomMilly&auth-token=%2B80PwdEniX1XRrydv/J87Q=='
wget -O rest  'http://localhost:52757/douckonline1/rest/student/lessons?username=MomMilly&auth-token=%2B80PwdEniX1XRrydv/J87Q=='
wget -O rest  'http://localhost:52757/douckonline1/rest/student/balance?username=MomMilly&auth-token=%2B80PwdEniX1XRrydv/J87Q=='

echo Lector
wget -O rest  'http://localhost:52757/douckonline1/rest/lector/profile?username=JamieHydrogen&auth-token=s7uRj0qs3%2Bd5DmjNhQO3jQ=='
wget -O rest  'http://localhost:52757/douckonline1/rest/lector/requests?username=JamieHydrogen&auth-token=s7uRj0qs3%2Bd5DmjNhQO3jQ=='
wget -O rest  'http://localhost:52757/douckonline1/rest/lector/teachings?username=JamieHydrogen&auth-token=s7j0qs3%2Bd5DmjNhQO3jQ=='
wget -O rest  'http://localhost:52757/douckonline1/rest/lector/lessons?username=JamieHydrogen&auth-token=s7uRqs3%2Bd5DmjNhQO3jQ=='

