"use strict";



module.exports = function (grunt) {

	var BUILD_DIR = grunt.option('build-dir') || process.env.NGINX_HOME + '/html/sprintifier',
		APP_DIR = grunt.option('app-dir') || 'app',
		BUILD_PATH = BUILD_DIR + "/",
		LIB_DIR = 'libs';
 
    grunt.initConfig({
	
        pkg: grunt.file.readJSON('package.json'),
		
		CFG: {
			SRC_DIR: APP_DIR,
			BUILD_DIR: BUILD_DIR
		},
 
        clean: {
        	build: [BUILD_DIR],
        	options: {
        		force: true
        	}
        },
 
		useminPrepare: {
			html: [BUILD_DIR + '/index.html'],
			options: {
				dest: BUILD_DIR
			}
		},
		
        usemin: {
            html: [BUILD_DIR + '/index.html']
        },
		
        uglify: {
			options: {
				compress: true,
				mangle: true // Use if you want the names of your functions and variables unchanged
			}
		},
		
		// automatically inject Bower components into the app
		wiredep: {
			dist: {
				src: ['<%= CFG.BUILD_DIR %>/index.html'],

				overrides: {
					
					"angular-i18n": {
						main: 'angular-locale_ru-ru.js'
					}
				},

				fileTypes: {
					html: {
						replace: {
							js: function (filePath) {
								if(filePath.indexOf('min.js') === -1) {
									filePath = filePath.substring(0, filePath.length - 2);
									filePath += 'js';
								}

								return '<script src="/sprintifier' + filePath.split("/app")[1] + '"></script>';
							}
						}
					}
				}
			}
		},

		// automatically inject user components into the app
		fileblocks: {
			dist: {
				src: ['<%= CFG.BUILD_DIR %>/index.html'],

				blocks: [
					{
						name: 'app',
						rename: function (file, path) {
							return '/sprintifier/' + path.replace(BUILD_PATH.replace(/\\/g,'/'), '').replace(/\/\//g, '/');
						},
						src: [
							'<%= CFG.BUILD_DIR %>/scripts/app.js',
							'<%= CFG.BUILD_DIR %>/scripts/routes.js',
							'<%= CFG.BUILD_DIR %>/scripts/**/*.js'
						]
					},
					{
						name: 'styles',
						rename: function (file, path) {
							return '/sprintifier/' + path.replace(BUILD_PATH.replace(/\\/g,'/'), '').replace(/\/\//g, '/');
						},
						src: [
							'<%= CFG.BUILD_DIR %>/styles/**/*.css',
							'<%= CFG.BUILD_DIR %>/' + LIB_DIR + '/**/*min.css'
						]
					}
				]
				
			}
		},
		
		copy: {
           scripts: {
				files: [
					{
						expand: true,
						cwd: APP_DIR + '/scripts',
						src: ['**/*.js'],
						dest: BUILD_DIR + '/scripts',
						filter: 'isFile'
					}
				]
			},
			libs: {
				files: [
					{
						expand: true,
						cwd: LIB_DIR,
						src: ['**/*min.js','**/*min.css'],
						dest: BUILD_DIR + '/' + 'LIB_DIR',
						filter: 'isFile'
					}
				]
			},
			styles: {
				files: [
					{
						expand: true,
						cwd: APP_DIR + '/styles',
						src: ['**/*.css'],
						dest: BUILD_DIR + '/styles',
						filter: 'isFile'
					},
					{
						expand: true,
						cwd: LIB_DIR + '/bootstrap/dist/css',
						src: [
							'bootstrap.min.css', 
							'bootstrap.min.map',
							'bootstrap-theme.min.css', 
							'bootstrap-theme.min.map'
						],
						dest: BUILD_DIR + '/styles/bootstrap',
						filter: 'isFile'
					}
				]
			},
			resources: {
				files: [
					{
						expand: true,
						cwd: APP_DIR + '/resources',
						src: '**/*',
						dest: BUILD_DIR + '/resources',
						filter: 'isFile'
					}
				]
			},
			views: {
				files: [
					{
						src : APP_DIR + '/index.html', 
						dest: BUILD_DIR + '/index.html'
					},
					{
						expand: true,
						cwd: APP_DIR + '/views',
						src: '**/*',
						dest: BUILD_DIR + '/views',
						filter: 'isFile'
					}
				]
			},
			bower: {
				files: [
					{
						expand: true,
						cwd: APP_DIR + '/libs',
						src: ['**/*.js','**/*.css','**/*.map'],
						dest: BUILD_DIR + '/' + LIB_DIR,
						filter: 'isFile'
					}
				]
			}
        },
		
		watch: {
			options: {
				dateFormat: function (ms) {
					var now = new Date(),
						time = now.toLocaleTimeString(),
						day = now.getDate(),
						month = now.getMonth() + 1,
						year = now.getFullYear();

					if (day < 10) {
						day = '0' + day;
					}

					if (month < 10) {
						month = '0' + month;
					}

					grunt.log.subhead(
						'Completed in ' + Math.round(ms) + 'ms at ' + time + ' ' +
						day + '.' + month + '.' + year + '.\n' +
						'Waiting for more changes...'
					);
				}
			},
			configFiles: {
				options: {
					reload: true
				},
				files: ['gruntfile.js', 'package.json'],
				tasks: ['newer:jshint:configFiles']
			},
			livereload: {
				options: {
					livereload: true
				},
				files: [BUILD_DIR + '/**/*']
			},
			scripts: {
				files: [APP_DIR + '/scripts/**/*.js'],
				tasks: ['newer:copy:scripts']
			},
			styles: {
				files: [APP_DIR + '/styles/**/*.css'],
				tasks: ['newer:copy:styles']
			},
			views: {
				files: [APP_DIR + '/index.html', APP_DIR + '/views/**/*.html', APP_DIR + '/views/**/*.htm'],
				tasks: ['newer:copy:views']
			},
			resources: {
				files: [APP_DIR + '/resources/**/*'],
				tasks: ['newer:copy:resources']
			}
		},
		
		bump: {
			options: {
				files: ['package.json'],
				updateConfigs: ['pkg'],
				commit: false,
				commitMessage: false,
				commitFiles: false,
				createTag: false,
				tagName: false,
				tagMessage: false,
				push: false,
				pushTo: false,
				gitDescribeOptions: false
			}
		}
		
    });
	
	require('load-grunt-tasks')(grunt);
 
	// Tell Grunt what to do when we type "grunt" into the terminal
    grunt.registerTask('default', ['clean', 'uglify', 'copy', 'wiredep', 'fileblocks', 'watch', 'bump']);
	
	grunt.registerTask('prod', ['clean', 'useminPrepare', 'usemin', 'uglify', 'copy', 'wiredep', 'fileblocks',  'browserSync', 'watch', 'bump']);
	
};