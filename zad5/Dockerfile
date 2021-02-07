FROM php:8.0.1-cli

RUN apt-get update && apt-get install -y --fix-missing \
    apt-utils \
    gnupg \
    zip \
    unzip

RUN echo "deb http://packages.dotdeb.org jessie all" >> /etc/apt/sources.list
RUN echo "deb-src http://packages.dotdeb.org jessie all" >> /etc/apt/sources.list
RUN curl -sS --insecure https://www.dotdeb.org/dotdeb.gpg | apt-key add -

RUN apt-get update && apt-get install -y \
    zlib1g-dev \
    libzip-dev

RUN docker-php-ext-install zip
COPY . /var/www/html

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/bin --filename=composer
COPY --from=composer /usr/bin/composer /usr/bin/composer

WORKDIR /var/www/html

RUN composer install

